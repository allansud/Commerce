package br.com.commerce.itemvenda;

import java.util.List;

import br.com.commerce.dao.ItemVendaDAO;

public class ItemVendaRN {
	
	private ItemVendaDAO itemVendaDAO;
	
	public ItemVendaRN(){
		if (itemVendaDAO == null) {
			itemVendaDAO = new ItemVendaDAO();
		}
	}
	
	public void salvar(ItemVenda itemVenda){
		itemVendaDAO.save(itemVenda);
	}
	
	public void alterar(ItemVenda itemVenda){
		itemVendaDAO.update(itemVenda);
	}
	
	public void deletar(ItemVenda itemVenda){
		itemVendaDAO.delete(itemVenda);
	}
	
	public List<ItemVenda> listar(){
		List<ItemVenda> itemVendas = null;
		try {
			itemVendas = itemVendaDAO.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemVendas;
	}

}
