package br.com.calculoproduto.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

import br.com.calculoproduto.eao.ImagemProdutoEAO;
import br.com.calculoproduto.eao.ProdutoEAO;
import br.com.calculoproduto.entity.ImagemProduto;
import br.com.calculoproduto.entity.Produto;
import br.com.calculoproduto.validador.ValidadorProduto;

public class ProdutoService {

	private ProdutoEAO eao = new ProdutoEAO();
	
	public Produto findProduto(Long idProduto) {
		return eao.find(idProduto);
	}
	
	public String salvarProduto(Produto produto, List<File> imagens) {
		
		try {
			ValidadorProduto validor = new ValidadorProduto();
			validor.validarCamposObrigatorios(produto);
			
			eao.saveOrUpdate(produto);
			gravarImagemProduto(produto, imagens);
			
			return null;
		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null, e.getMessage());
			removerProduto(produto);
			return e.getMessage();			
		} 
	}
	
	private void removerProduto(Produto produto) {
		if (produto != null && produto.getIdProduto() != null) {
			ProdutoEAO produtoEAO = new ProdutoEAO();
			removerImagemProduto(produto);
			produtoEAO.delete(produto);
		}
	}

	private void removerImagemProduto(Produto produto) {
		ImagemProdutoEAO imagemProdutoEAO = new ImagemProdutoEAO();
		Set<ImagemProduto> imagensProduto = produto.getImagensProduto();
		
		if (imagensProduto != null && !imagensProduto.isEmpty()) {
			imagemProdutoEAO.delete(imagensProduto);
		}
	}

	private void gravarImagemProduto(Produto produto, List<File> imagens) {

		try {
			ImagemProdutoEAO eao = new ImagemProdutoEAO();
			for (File file : imagens) {
				ImagemProduto imagemProduto = new ImagemProduto();
				
				BufferedImage img = ImageIO.read(file);
				
				imagemProduto.setProduto(produto);
				imagemProduto.setImagem(getImgBytes(img));
				
				eao.saveOrUpdate(imagemProduto);
				produto.getImagensProduto().add(imagemProduto);
			}
		} catch (Exception e) {
			removerImagemProduto(produto);
			throw new RuntimeException(e.getMessage());
		}
	}
	
    private static byte[] getImgBytes(BufferedImage image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "JPEG", baos);
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        
        return baos.toByteArray();
    }
}
