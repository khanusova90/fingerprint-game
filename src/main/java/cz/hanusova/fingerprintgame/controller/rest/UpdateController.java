/**
 * 
 */
package cz.hanusova.fingerprintgame.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.hanusova.fingerprintgame.model.Item;
import cz.hanusova.fingerprintgame.repository.ItemRepository;

/**
 * Controller for updating information from client
 * 
 * @author khanusova
 *
 */
@RestController
@RequestMapping("/update")
public class UpdateController {

	@Autowired
	private ItemRepository itemTypeRepository;

	@RequestMapping("/itemTypes")
	public List<Item> getItemTypes() {
		return itemTypeRepository.findAll();
	}

}
