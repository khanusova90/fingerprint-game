package cz.hanusova.fingerprintgame.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.hanusova.fingerprintgame.model.Resource;
import cz.hanusova.fingerprintgame.repository.ResourceRepository;
import cz.hanusova.fingerprintgame.service.ResourceService;
import cz.hanusova.fingerprintgame.utils.UserUtils;

@Service
public class ResourceServiceImpl implements ResourceService {
	
	private static final Log logger = LogFactory.getLog(ResourceServiceImpl.class);
	
	@Autowired
	private ResourceRepository resourceRepository;
	
	@Override
	public void saveResources(Resource[] resources){
		logger.info("Uzivatel " + UserUtils.getActualUsername() + " uklada nove zdroje surovin");
		
		for (Resource res : resources){
			//TODO: zkontrolovat, jestli uz suroviny ulozene nejsou
			resourceRepository.save(res);
		}
	}

}
