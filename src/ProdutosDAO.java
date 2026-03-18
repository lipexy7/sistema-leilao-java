/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProdutosDAO {
    
    conectaDAO conexao = new conectaDAO();
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    // 1. Método para Cadastrar Produto
    public void cadastrarProduto(ProdutosDTO produto) {
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        
        try {
            prep = conexao.connectDB().prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            
            prep.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + e.getMessage());
        }
    }

    // 2. Método para Listar Produtos
    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
    // A query SQL agora tem o filtro WHERE
    String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
    
    // Limpa a lista antes de preencher para não duplicar dados na tela
    listagem.clear(); 
    
    try {
        prep = conexao.connectDB().prepareStatement(sql);
        resultset = prep.executeQuery();
        
        while (resultset.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            
            produto.setId(resultset.getInt("id"));
            produto.setNome(resultset.getString("nome"));
            produto.setValor(resultset.getInt("valor"));
            produto.setStatus(resultset.getString("status"));
            
            listagem.add(produto);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar produtos vendidos: " + e.getMessage());
    }
    
    return listagem;
}

    // 3. Método para Vender Produto (O que você tinha feito por último!)
    public void venderProduto(int id) {
    String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
    try {
        prep = conexao.connectDB().prepareStatement(sql);
        prep.setInt(1, id);
        prep.executeUpdate();
        JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao vender: " + e.getMessage());
    }
}
}

// Funciona pelo amor de Deus :(
// Funcionou :D