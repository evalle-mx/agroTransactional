/*
 * Copyright 2010-2011 Nabeel Mukhtar 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 *  http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 * 
 */
package net.tce.util;

import java.text.MessageFormat;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import com.google.code.linkedinapi.client.LinkedInApiClient;
import com.google.code.linkedinapi.client.LinkedInApiClientFactory;
import com.google.code.linkedinapi.client.enumeration.ProfileField;
import com.google.code.linkedinapi.client.enumeration.ProfileType;
import com.google.code.linkedinapi.schema.Company;
import com.google.code.linkedinapi.schema.Country;
import com.google.code.linkedinapi.schema.DateOfBirth;
import com.google.code.linkedinapi.schema.Education;
import com.google.code.linkedinapi.schema.Educations;
import com.google.code.linkedinapi.schema.EndDate;
import com.google.code.linkedinapi.schema.Language;
import com.google.code.linkedinapi.schema.Languages;
import com.google.code.linkedinapi.schema.Location;
import com.google.code.linkedinapi.schema.Person;
import com.google.code.linkedinapi.schema.PhoneNumber;
import com.google.code.linkedinapi.schema.PhoneNumbers;
import com.google.code.linkedinapi.schema.Position;
import com.google.code.linkedinapi.schema.Positions;
import com.google.code.linkedinapi.schema.Skill;
import com.google.code.linkedinapi.schema.Skills;
import com.google.code.linkedinapi.schema.StartDate;
/*
 * CLASE DE PRUEBAS UNICAMENTE PARA PROBAR LA CONECTIVIDAD CON LINKED-IN [TODO ELIMINAR EN PRODUCTIVO]
 * 
 * Procesa un request para solicitar un objeto perfil-Persona, utilizando las llaves y token's de validación
 * 
 *  fuente: https://code.google.com/p/linkedin-j/source/browse/#svn%2Ftrunk%2Flinkedin-j%2Fexamples%2Fsrc%2Fmain%2Fjava%2Fcom%2Fgoogle%2Fcode%2Flinkedinapi%2Fclient%2Fexamples
 */
/**
 * @author Nabeel Mukhtar
 *
 */
public class ProfileApiExample {

    /**
     * Consumer Key
     */
    private static final String CONSUMER_KEY_OPTION = "consumerKey";	//75qtiw3wde5zy7 | consumerKey
        
    /**
     * Consumer Secret
     */
    private static final String CONSUMER_SECRET_OPTION = "consumerSecret";	//RQht7EPLEReRC8ow | consumerSecret
    
    /**
     * Access Token
     */
    private static final String ACCESS_TOKEN_OPTION = "token";	//7503dd46b1fb62489d844c65f4252061b4 | token
        
    /**
     * Access Token Secret
     */
    private static final String ACCESS_TOKEN_SECRET_OPTION = "tokenSecret";	//1076debf6282435989e49b4e372c9d51 | tokenSecret

    /**
     * ID
     */
    private static final String ID_OPTION = "id";
    
    /**
     * Profile Type
     */
    private static final String TYPE_OPTION = "type";
    
    /**
     * URL
     */
    private static final String URL_OPTION = "url";
    
    /**
     * Name of the help command line option.
     */
    private static final String HELP_OPTION = "help";
    
    /**
         * @param args
        
        @SuppressWarnings("unused")
		public static void main(String[] args) {
                Options options = buildOptions();
        try {
            CommandLine line = new BasicParser().parse(options, args);
//            processCommandLine(line, options);
            processCommandLineSimple();
        } catch(ParseException exp ) {
            System.err.println(exp.getMessage());
            printHelp(options);
        }
        } */
        
    /**
     * Process command line options and call the service. 
     */
     protected static void processCommandLine(CommandLine line, Options options) {
        if(line.hasOption(HELP_OPTION)) {
            printHelp(options);            
        } else if(line.hasOption(CONSUMER_KEY_OPTION) && line.hasOption(CONSUMER_SECRET_OPTION)
                        && line.hasOption(ACCESS_TOKEN_OPTION) && line.hasOption(ACCESS_TOKEN_SECRET_OPTION)) {
        	System.out.println("------------------------------- calling The Service  ------------------------ ");
                final String consumerKeyValue = line.getOptionValue(CONSUMER_KEY_OPTION);
                final String consumerSecretValue = line.getOptionValue(CONSUMER_SECRET_OPTION);
                final String accessTokenValue = line.getOptionValue(ACCESS_TOKEN_OPTION);
                final String tokenSecretValue = line.getOptionValue(ACCESS_TOKEN_SECRET_OPTION);
                
                final LinkedInApiClientFactory factory = LinkedInApiClientFactory.newInstance(consumerKeyValue, consumerSecretValue);
                final LinkedInApiClient client = factory.createLinkedInApiClient(accessTokenValue, tokenSecretValue);
                
                ProfileType profileType = ProfileType.STANDARD;
                if (line.hasOption(TYPE_OPTION)) {
                        profileType = ProfileType.valueOf(line.getOptionValue(TYPE_OPTION));
                }
                
                if(line.hasOption(ID_OPTION)) {
                        String idValue = line.getOptionValue(ID_OPTION);
                        System.out.println("Fetching profile for user with id:" + idValue);
                        Person profile = client.getProfileById(idValue);
                        printResult(profile);
                } else if (line.hasOption(URL_OPTION)) {
                        String urlValue = line.getOptionValue(URL_OPTION);
                        System.out.println("Fetching profile for user with url:" + urlValue);
                        Person profile = client.getProfileByUrl(urlValue, profileType);
                        printResult(profile);
                } else {
                        System.out.println("Fetching profile for current user.");
                        Person profile = client.getProfileForCurrentUser(EnumSet.allOf(ProfileField.class));
                        printResult(profile);
                }
        } else {
        	System.out.println("------------------------------- printHelp(options)  ------------------------ ");
            printHelp(options);
        }
    }
    
    /**
     * Process command line options and call the service. 
     */
    protected static void processCommandLineSimple() {
        	System.out.println("------------------------------- calling The Service  ------------------------ ");
                final String consumerKeyValue = "75qtiw3wde5zy7";
                final String consumerSecretValue = "RQht7EPLEReRC8ow";
                final String accessTokenValue = "c5579efb-0619-400e-bffb-dc05b674f3b5";
                final String tokenSecretValue = "8b132106-886c-414a-b4ad-cff8f95bd21a";
                
                final LinkedInApiClientFactory factory = LinkedInApiClientFactory.newInstance(consumerKeyValue, consumerSecretValue);
                final LinkedInApiClient client = factory.createLinkedInApiClient(accessTokenValue, tokenSecretValue);
                                                             
                System.out.println("Fetching profile for current user.");
                Person profile = client.getProfileForCurrentUser(EnumSet.allOf(ProfileField.class));                
                printResult(profile);
//                System.out.println("\n*****************************************************************\n");
//                System.out.println("Fetching profile for other user by URL.");
//                Person contact = client.getProfileByUrl(Constante.URL_OSOTO, ProfileType.STANDARD, EnumSet.allOf(ProfileField.class));//OK
//                		//client.getProfileByUrl(Constante.URL_ADM2, ProfileType.STANDARD, EnumSet.allOf(ProfileField.class));//OK
//                		//client.getProfileById("76903031", EnumSet.allOf(ProfileField.class)); *Fail
//                
//                printResult(contact);
    }
        
        /**
     * Build command line options object.
     */
   /* private static Options buildOptions() {
       
        Options opts = new Options();
        
        String helpMsg = "Print this message.";
        Option help = new Option(HELP_OPTION, helpMsg);
        opts.addOption(help);

        String consumerKeyMsg = "You API Consumer Key.";
        OptionBuilder.withArgName("consumerKey");
        OptionBuilder.hasArg();
        OptionBuilder.withDescription(consumerKeyMsg);
        Option consumerKey = OptionBuilder.create(CONSUMER_KEY_OPTION);
        opts.addOption(consumerKey);
        
        String consumerSecretMsg = "You API Consumer Secret.";
        OptionBuilder.withArgName("consumerSecret");
        OptionBuilder.hasArg();
        OptionBuilder.withDescription(consumerSecretMsg);
        Option consumerSecret = OptionBuilder.create(CONSUMER_SECRET_OPTION);
        opts.addOption(consumerSecret);
        
        String accessTokenMsg = "You OAuth Access Token.";
        OptionBuilder.withArgName("accessToken");
        OptionBuilder.hasArg();
        OptionBuilder.withDescription(accessTokenMsg);
        Option accessToken = OptionBuilder.create(ACCESS_TOKEN_OPTION);
        opts.addOption(accessToken);
        
        String tokenSecretMsg = "You OAuth Access Token Secret.";
        OptionBuilder.withArgName("accessTokenSecret");
        OptionBuilder.hasArg();
        OptionBuilder.withDescription(tokenSecretMsg);
        Option accessTokenSecret = OptionBuilder.create(ACCESS_TOKEN_SECRET_OPTION);
        opts.addOption(accessTokenSecret);
        
        String idMsg = "ID of the user whose profile is to be fetched.";
        OptionBuilder.withArgName("id");
        OptionBuilder.hasArg();
        OptionBuilder.withDescription(idMsg);
        Option id = OptionBuilder.create(ID_OPTION);
        opts.addOption(id);
        
        String urlMsg = "Profile URL of the user whose profile is to be fetched.";
        OptionBuilder.withArgName("url");
        OptionBuilder.hasArg();
        OptionBuilder.withDescription(urlMsg);
        Option url = OptionBuilder.create(URL_OPTION);
        opts.addOption(url);
        
        String typeMsg = "Type of profile. Either standard or public.";
        OptionBuilder.withArgName("type");
        OptionBuilder.hasArg();
        OptionBuilder.withDescription(typeMsg);
        Option type = OptionBuilder.create(TYPE_OPTION);
        opts.addOption(type);
        
        return opts;
    }*/
    
    /**
     * Print help and usage.
     */
    private static void printHelp(Options options) {
        int width = 80;
        String syntax = ProfileApiExample.class.getName() + " <options>";
        String header = MessageFormat.format("\nThe -{0}, -{1}, -{2} and -{3} options are required. All others are optional.", CONSUMER_KEY_OPTION, CONSUMER_SECRET_OPTION, ACCESS_TOKEN_OPTION, ACCESS_TOKEN_SECRET_OPTION);
        String footer = MessageFormat.format("\nIf you do not specify any of -{0} or -{1} options, the profile of current user is returned. You can only specify one of these options. If you do not specify -{2} option, standard profile is returned.", ID_OPTION, URL_OPTION, TYPE_OPTION);
        new HelpFormatter().printHelp(width, syntax, header, options, footer, false);
    }
    
    
    /**
     * Print the result of API call.
     */
    protected static void printResult(Person profile) {
        System.out.println("================================");
        System.out.println("ID: " + profile.getId() );
        System.out.println("Name: " + profile.getFirstName() + " " + profile.getLastName());
        System.out.println("Headline: " + profile.getHeadline());
        System.out.println("Summary: " + profile.getSummary());
        System.out.println("Industry: " + profile.getIndustry());
        System.out.println("Picture: " + profile.getPictureUrl());
        System.out.println("MainAddress: " + profile.getMainAddress() );
        System.out.println("Public URL: " + profile.getPublicProfileUrl() );
        System.out.println("Status: " + profile.getCurrentStatus() );//
        DateOfBirth dateOfBirth = profile.getDateOfBirth();
        if(dateOfBirth!=null){
        	System.out.println("DateOfBirth: " + dateOfBirth.getDay() + "/" + dateOfBirth.getMonth() + "/" + dateOfBirth.getYear());
        }
        /*  Location /Location  */
        Location loc = profile.getLocation();
        if(loc!=null){
        	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n >> Location");
            System.out.println("\t Location.name:" + loc.getName() );
            System.out.println("\t Location.address:" + loc.getAddress() );
            System.out.println("\t Location.postalCode:" + loc.getPostalCode() );
            //System.out.println("\t Location.Country:" + loc.getCountry() );
            Country country = loc.getCountry();
            System.out.println("\t Location.Country.code:" + country.getCode() );
        }
        
        /* Languagues / ? (Idiomas) */
        Languages langs = profile.getLanguages();
        if(langs!=null){
        	List<Language> lsLanguage = langs.getLanguageList();
            if(lsLanguage!=null){
            	Iterator<Language> itLanguage = lsLanguage.iterator();
            	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n >> Languages");
                while(itLanguage.hasNext()){
                	Language language = itLanguage.next();
                	System.out.println("\t Id " + language.getId() );
                	System.out.println("\t Language.name " + language.getLanguage().getName() );
                	if(language.getProficiency()!=null){
                		System.out.println("\t Proficiency.name " + language.getProficiency(). getName());
                		System.out.println("\t Proficiency.level " + language.getProficiency().getLevel());
                	}
                }
            }
        }
        
        /* PhoneNumbers / Contact (contactos) */
        PhoneNumbers phoneNumbers = profile.getPhoneNumbers();
        if(phoneNumbers!=null){
        	List<PhoneNumber> lsPhoneNumber = phoneNumbers.getPhoneNumberList();
            if(lsPhoneNumber!=null){
            	Iterator<PhoneNumber> itPhoneNumber = lsPhoneNumber.iterator();
            	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n >> PhoneNumbers");
                while(itPhoneNumber.hasNext()){
                	PhoneNumber phonenumber = itPhoneNumber.next();
                	System.out.println("\t Number " + phonenumber.getPhoneNumber());
                	System.out.println("\t Type " + phonenumber.getPhoneType().name());
                }
            }
        }
        
        /*  Posiciones /Experiencia */
        Positions positions = profile.getPositions();        
        if(positions!=null){
        	List<Position> lsPosition = positions.getPositionList();
            if(lsPosition!=null){
            	Iterator<Position> itPosition = lsPosition.iterator();
            	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n >> Posiciones");
                while(itPosition.hasNext()){
                	Position position = itPosition.next();
                	System.out.println();
                	Company company = position.getCompany();   
                	if(company!=null){
                		System.out.println("\t Company.Name: " + company.getName());
                		System.out.println("\t Company.id: " + company.getId() );
                		System.out.println("\t Company.Description: " + company.getDescription());
                		System.out.println("\t Company.Industry: " + company.getIndustry());
                	}
                	System.out.println("\t Title: " + position.getTitle());
                	             	
                	System.out.println("\t Summary: " + position.getSummary() );
                	System.out.println("\t SkillsAndExperience: " + position.getSkillsAndExperience() );
                	System.out.println("\t Description: " + position.getDescription());
                	StartDate startDate = position.getStartDate();
                	if(startDate!=null){
                		System.out.println("\t StartDate: " + startDate.getDay()+"/"+startDate.getMonth()+"/"+startDate.getYear() );
                	}
                	
                	EndDate endDate = position.getEndDate();
                	if(endDate!=null){
                		System.out.println("\t EndDate: " + endDate.getDay()+"/"+endDate.getMonth()+"/"+endDate.getYear() );
                	}
                	
                	System.out.println("\t ExperienceLevel: " + position.getExperienceLevel() );
                	
                	
                }
            }
        }
        
        /* Educaciones / Academias */
        Educations educations = profile.getEducations();
        if(educations!=null){
        	List<Education> lsEducation = educations.getEducationList();
            if(lsEducation!=null){
            	Iterator<Education> itEducation = lsEducation.iterator();
            	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n >> Educaciones");
                while(itEducation.hasNext()){
                	Education education = itEducation.next();
                	System.out.println("\t FieldOfStudy: " + education.getFieldOfStudy());
                	System.out.println("\t School: " + education.getSchoolName());
                	StartDate startDateE = education.getStartDate();
                	EndDate endDateE = education.getEndDate();
                	if(startDateE!=null){
                		System.out.println("\t StartDate: " + startDateE.getDay()+"/"+startDateE.getMonth()+"/"+startDateE.getYear() );
                	}
                	if(endDateE!=null){
                		System.out.println("\t EndDate: " + endDateE.getDay()+"/"+endDateE.getMonth()+"/"+endDateE.getYear() );
                	}
                	System.out.println("\t Activities " + education.getActivities());
                	System.out.println("\t Notes: " + education.getNotes());
                }
            }
        }
        
        Skills skills = profile.getSkills();
        if(skills!=null){
        	List<Skill> lsSkills = skills.getSkillList();
        	if(lsSkills!=null){
        		Iterator<Skill> itSkill = lsSkills.iterator();
            	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n >> Skills");
                while(itSkill.hasNext()){
                	Skill skill = itSkill.next();
                	System.out.println("\t ID: " + skill.getId() );
                	System.out.println("\t Name: " + skill.getSkill().getName());
//                	System.out.println("\t years.id: " + skill.getYears().getId() );
                }
        	}
        }
        
        
	}
    
}