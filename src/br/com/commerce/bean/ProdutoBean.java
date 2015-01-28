package br.com.commerce.bean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;

import br.com.commerce.categoria.Categoria;
import br.com.commerce.fabricante.Fabricante;
import br.com.commerce.produto.Produto;
import br.com.commerce.produto.ProdutoRN;
import br.com.commerce.vendedor.Vendedor;

@ManagedBean
@ViewScoped
public class ProdutoBean {
	
	private Produto produto = new Produto();
	private List<Produto> listarProdutos;
	ProdutoRN produtoRN = new ProdutoRN();
	private String fabricanteEscolhido;
	private String categoriaEscolhida;
	private String vendedorEscolhido;
	private Categoria categoria = new Categoria();
	
	public List<Produto> getListaProdutos() {
		if(listarProdutos == null){
			listarProdutos = produtoRN.listar();
		}
		return listarProdutos;
	}
	
	public Produto carregaProduto(){
		return produto = produtoRN.buscaPorID(produto.getCodigo());
	}
	
	public List<Produto> listarPromocoes(){
		return produtoRN.listaPromocoes();
	}
	
	public void deletar(Produto produto){
		produtoRN.deletar(produto);
		this.listarProdutos = null;
	}
	
	public List<Produto> listarProdutoPorCategoria(){
		return produtoRN.listarPorCategoria(categoria.getCodigo());
	}
	
	public String salvar(){
		
		try {
			
			if(produto.getDescricao() != null || produto.getNome() != null){
				
				Locale locale = new Locale("pt", "BR");
				GregorianCalendar calendario = new GregorianCalendar();
				SimpleDateFormat formatador = new SimpleDateFormat("dd' de 'MMMMM' de 'yyyy' - 'HH':'mm'h'", locale); 
				produto.setData_cadastro(formatador.format(calendario.getTime()));								
				
				produto.setFoto1((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("foto_1"));
				produto.setFoto2((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("foto_2"));
				produto.setFoto3((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("foto_3"));
				produto.setFoto4((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("foto_4"));				
				
				Fabricante f = new Fabricante();
				f.setCodigo(Long.valueOf(getFabricanteEscolhido()));
				produto.setFabricante(f);
				
				Categoria categoria = new Categoria();
				categoria.setCodigo(Long.valueOf(getCategoriaEscolhida()));
				produto.setCategoria(categoria);
				
				Vendedor v = new Vendedor();
				v.setId(Long.parseLong(getVendedorEscolhido()));
				produto.setVendedor(v);
				
				produtoRN.salvar(produto);
				this.produto = new Produto();
				
	            FacesMessage acerto = new FacesMessage("Aviso:",
	                    "Produto foi salvo!");
	            FacesContext.getCurrentInstance().addMessage(null, acerto);								
			}					
			
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("foto_1");
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("foto_2");
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("foto_3");
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("foto_4");
			
		} catch (Exception e) {
			
            FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Produto não foi salvo!!!!", "");
            FacesContext.getCurrentInstance().addMessage(null, error);
		}
		return "salvo";
	}
	
    public void handleFileUpload(FileUploadEvent event) {
        
    	String nome = event.getFile().getFileName();
    	
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        
        String caminho = extContext.getRealPath("//resources//produtos//" + event.getFile().getFileName());;
        File result = new File(caminho);
        
        if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("foto_1") == null){
        	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("foto_1", nome);
        }
        
        if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("foto_1") != null){
        	if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("foto_2") == null){
        		String rota = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("foto_1");
        		String rota_2 = nome;
        		if(rota_2 != rota){
        			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("foto_2", nome);
        		}
        	}
        }
        
        if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("foto_2") != null){
        	if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("foto_3") == null){
        		String rota = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("foto_2");
        		String rota_2 = nome;
        		if(rota_2 != rota){
        			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("foto_3", nome);
        		}
        	}
        }
        
        if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("foto_3") != null){
        	if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("foto_4") == null){
        		String rota = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("foto_3");
        		String rota_2 = nome;
        		if(rota_2 != rota){
        			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("foto_4", nome);
        		}
        	}        	
        }
        
        try {
        	
            FileOutputStream fileOutputStream = new FileOutputStream(result);

            byte[] buffer = new byte[6124];

            int bulk;
            InputStream inputStream = event.getFile().getInputstream();
            while (true) {
                bulk = inputStream.read(buffer);
                if (bulk < 0) {
                    break;
                }
                fileOutputStream.write(buffer, 0, bulk);
                fileOutputStream.flush();
            }

            fileOutputStream.close();
            inputStream.close();

            FacesMessage msg = 
                        new FacesMessage("Descricao do Arquivo", "Nome do Arquivo: " +
                        event.getFile().getFileName() + " Tamanho do Arquivo: " + 
                        event.getFile().getSize() / 1024 + " Kb - Tipo de Arquivo: " + 
                        event.getFile().getContentType() + " Arquivo foi enviado!");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } catch (IOException e) {
            e.printStackTrace();

            FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                           "O arquivo nao foi enviado", "");
            FacesContext.getCurrentInstance().addMessage(null, error);
        }       
    }

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public String getFabricanteEscolhido() {
		return fabricanteEscolhido;
	}

	public void setFabricanteEscolhido(String fabricanteEscolhido) {
		this.fabricanteEscolhido = fabricanteEscolhido;
	}

	public String getCategoriaEscolhida() {
		return categoriaEscolhida;
	}

	public void setCategoriaEscolhida(String categoriaEscolhida) {
		this.categoriaEscolhida = categoriaEscolhida;
	}

	/**
	 * @return the vendedorEscolhido
	 */
	public String getVendedorEscolhido() {
		return vendedorEscolhido;
	}

	/**
	 * @param vendedorEscolhido the vendedorEscolhido to set
	 */
	public void setVendedorEscolhido(String vendedorEscolhido) {
		this.vendedorEscolhido = vendedorEscolhido;
	}

	/**
	 * @return the categoria
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}
