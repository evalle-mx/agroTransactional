package net.tce.startup;


//import java.util.Iterator;
//import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import net.tce.admin.service.EmpresaInterfaseService;
import net.tce.admin.service.FileServer;
import net.tce.cache.AvisoCache;
import net.tce.cache.EmpresaConfCache;
import net.tce.cache.FileCache;
import net.tce.dto.AvisoDto;
import net.tce.dto.EmpresaConfDto;
import net.tce.dto.TipoContenidoDto;
//import net.tce.model.Aviso;
//import net.tce.model.EmpresaConf;
//import net.tce.task.service.SchedulerClassifiedDocService;

/**
 * Clase que funciona como StartUp para inicializar condiciones necesarias al sistema
 * @author Goyo
 *
 */
public class AppStartUp {
	Logger log4j = Logger.getLogger( this.getClass());
	
	@Autowired
	private EmpresaInterfaseService empresaInterfaseService;
	
	@Autowired
	private FileServer fileServer;
	
//	@Autowired
//	private SchedulerClassifiedDocService schedulerClassifiedDocService;		
	
//	@Autowired
//	private EmpresaConfDao empresaConfDao;
	
//	@Autowired
//	private AvisoDao avisofDao;
	
	
		
	/**
	 * Se ejecuta el metodo despues de la inyección de dependencias de Spring 
	 */
	@PostConstruct
	public void initIt() {
		log4j.debug("<AppStartUp,initIt>  empresaInterfaseService="+empresaInterfaseService+
				"\n isNullChmContenido="+FileCache.isNullChmContenido()+
//				"\n empresaConfDao="+empresaConfDao+
				"\n EmpresaConfCache="+EmpresaConfCache.isEmpty()); 

		
	    //si es nulo el objeto_cache(ChmContenido), hay que obtenerlo
	    if(FileCache.isNullChmContenido()){
		   /* //Se obtienen los tipocontenidos de todas las empresas(idConf)
		    ConcurrentHashMap<String, TipoContenidoDto> chmContenido= empresaInterfaseService.getAllContenido();
		    log4j.debug("<AppStartUp,initIt> chmContenido="+chmContenido);
		    //Se pone en cache el map
		    if(chmContenido != null){
			    log4j.info("<AppStartUp,initIt> SE OBTUVIERON "+chmContenido.size()+" DATOS EMPRESA_PARAMETRO (tipo=Contenido) de XE SATISFACTORIAMENTE  %%%\n" );
		    	FileCache.setChmcontenido(chmContenido);
		    }//*/
	    	ConcurrentHashMap<String, TipoContenidoDto> chmContenido= new ConcurrentHashMap<String, TipoContenidoDto>();
	    	chmContenido.put("0_1",new  TipoContenidoDto(1,"jpg|png|bmp|gif|jpeg|",new Long(4000),new Long(200000)));//idTipoContenido=1,types=,minSize=4000,maxSize=200000]
	    	chmContenido.put("0_2",new  TipoContenidoDto(2,"jpg|png|bmp|gif|pdf|jpeg|",new Long(4000),new Long(200000)));
	    	chmContenido.put("0_3",new  TipoContenidoDto(3,"doc|docx|xls|xlsx|jpeg|",new Long(1000),new Long(10000000))); 
	    	log4j.info("<AppStartUp,initIt> SE insertaron 3 DATOS EMPRESA_PARAMETRO (tipo=Contenido) de XE SATISFACTORIAMENTE  %%%\n" );
	    }
	    
	    //Se obtienen los datos de empresaconf y se ponen en cache
	    //si es nulo el objeto_cache(EmpresaConf), hay que obtenerlo
	    if(EmpresaConfCache.isEmpty()){
	    	/*List<EmpresaConf> lsEmpresaConf=empresaConfDao.findAll();
		    log4j.debug("<AppStartUp,initIt> lsEmpresaConf="+lsEmpresaConf);
			if(lsEmpresaConf != null && lsEmpresaConf.size() > 0){
				Iterator<EmpresaConf> ltEmpresaConf=lsEmpresaConf.iterator();
				while(ltEmpresaConf.hasNext()){
					EmpresaConf empresaConf=ltEmpresaConf.next();
					log4j.debug("<AppStartUp> DUMMY-CREATE: " +
							"EmpresaConfCache.put("+empresaConf.getIdEmpresaConf()+
							", new EmpresaConfDto("+empresaConf.getIdEmpresaConf()+
							","+	empresaConf.getEmpresa().getIdEmpresa()+
							","+empresaConf.getConf().getIdConf()+") );");
					
					EmpresaConfCache.put(empresaConf.getIdEmpresaConf(),
										new EmpresaConfDto(empresaConf.getIdEmpresaConf(),
										empresaConf.getEmpresa().getIdEmpresa(),
										empresaConf.getConf().getIdConf()) );
				}
			    log4j.info("<AppStartUp,initIt> SE OBTUVIERON "+lsEmpresaConf.size()+" OBJETOS EMPRESA_CONF de XE SATISFACTORIAMENTE  %%%\n" );
			}else{
				log4j.fatal("<AppStartUp,initIt> No hay información en la tabla EMPRESA_CONF");
			}	//*/
	    	EmpresaConfCache.put(new Long(1), new EmpresaConfDto(new Long(1),new Long(1),new Long(0)) );
	    	EmpresaConfCache.put(new Long(2), new EmpresaConfDto(new Long(2),new Long(1),new Long(1)) );
	    	log4j.info("<AppStartUp,initIt> SE insertaron 2 OBJETOS EMPRESA_CONF de XE SATISFACTORIAMENTE  %%%\n" );
	    }
	    
	    //Se obtienen los objetos Aviso y se ponen en cache
	    if(AvisoCache.isEmpty()){
	    	/*List<Aviso> lsAviso=avisofDao.findAll();
	    	log4j.debug("<AppStartUp,initIt> lsAviso="+lsAviso);
	    	if(lsAviso != null && lsAviso.size() > 0){
				Iterator<Aviso> ltAviso=lsAviso.iterator();
				while(ltAviso.hasNext()){
					Aviso aviso=ltAviso.next();
					log4j.debug("<AppStartUp> DUMMY-CREATE: " +
							"AvisoCache.put("+aviso.getClaveAviso()+",new AvisoDto("+aviso.getIdAviso()+","+aviso.getClaveAviso()+",null,null) );"
							);
					AvisoCache.put(aviso.getClaveAviso(),
									new AvisoDto(aviso.getIdAviso(),
									aviso.getClaveAviso(),null,null) );
				}
			    log4j.info("<AppStartUp> SE OBTUVIERON "+lsAviso.size()+" OBJETOS AVISO de XE SATISFACTORIAMENTE  %%%\n" );
			}else{
				log4j.fatal("No hay información en la tabla AVISO");
			}	//*/
	    	AvisoCache.put("EMPTY_PER_TRAB",new AvisoDto(new Long(1),"EMPTY_PER_TRAB",null,null));
	    	AvisoCache.put("EMPTY_DISP_HORA",new AvisoDto(new Long(2),"EMPTY_DISP_HORA",null,null) );
	    	AvisoCache.put("EMPTY_CAMB_DOM",new AvisoDto(new Long(3),"EMPTY_CAMB_DOM",null,null) );
	    	AvisoCache.put("EMPTY_EDO_CIVIL",new AvisoDto(new Long(4),"EMPTY_EDO_CIVIL",null,null) );
	    	AvisoCache.put("WARN_TIPO_JORNADA",new AvisoDto(new Long(5),"WARN_TIPO_JORNADA",null,null) );
	    	AvisoCache.put("EMPTY_TIPO_JORNADA",new AvisoDto(new Long(6),"EMPTY_TIPO_JORNADA",null,null) );
	    	log4j.info("<AppStartUp> SE insertaron 6 OBJETOS AVISO de XE SATISFACTORIAMENTE  %%%\n" );
	    }
	    
	    //Borrar archivos fisicos en repositorio físico
	    fileServer.deleteAllRepository();
	    log4j.info("<AppStartUp> SE ELIMINARON LOS ARCHIVOS TEMPORALES EN EL REPOSITORIO %%% \n\t <<<<<< Fin de StartUp  >>>>>>>\n" );
	}


	
}
