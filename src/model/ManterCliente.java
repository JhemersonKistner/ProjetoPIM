/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Visao.TelaLogin;
import Visao.TelaMenuCliente;
import controle.cliente;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Jhemerson
 */
public class ManterCliente extends DAO {
    String loginus;
    public void inserir(cliente u) throws Exception{
        abrirBanco();
        String query = "INSERT INTO cliente(idclient,nome_cliente,cpf,rg,celular,senha)values(null,?,?,?,?,?)";
        ps = (PreparedStatement) con.prepareStatement(query);
        ps.setString(1, u.getNome_cliente());
        ps.setString(2, u.getCpf());
        ps.setString(3, u.getRg());
        ps.setString(4, u.getCelular());
        ps.setString(5, u.getSenha());
        ps.execute();
        JOptionPane.showMessageDialog(null,"Cadastro realizado com sucesso");
        fecharBanco();
    }

    public void loginCliente(cliente a) throws Exception{
        abrirBanco();
        String query = "select *from cliente where nome_cliente=? and senha=? ";
        ps = (PreparedStatement) con.prepareStatement(query);
        ps.setString(1, a.getNome_cliente());
        ps.setString(2, a.getSenha());        
        ps.execute();
        rs = ps.executeQuery();
        if(rs.next()){
                    TelaMenuCliente telamenucliente = new TelaMenuCliente();
                    TelaLogin telalogin = new TelaLogin();
                    telamenucliente.setVisible(true);
                    telalogin.setVisible(false);
                    login = a.getNome_cliente();        
        }else{
            JOptionPane.showMessageDialog(null,"usuario não identificado");
        }
        fecharBanco();       
    }
    
    public List<cliente> read(){
        List<cliente> clientes = new ArrayList<>();
        try {
            abrirBanco();
            String query = "select *from cliente";
            ps = (PreparedStatement) con.prepareStatement(query);
            ps.execute();
            rs = ps.executeQuery();
            
            
            
            while(rs.next()){
                cliente dados = new cliente();
                dados.setNome_cliente(rs.getString("nome_cliente"));
                dados.setCpf(rs.getString("cpf"));
                dados.setRg(rs.getString("rg"));
                dados.setCelular(rs.getString("celular"));
                dados.setSenha(rs.getString("senha"));
                clientes.add(dados);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManterCliente.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try { 
                fecharBanco();
            } catch (Exception ex) {
                Logger.getLogger(ManterCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return clientes;
        
    }
    
    public void PesquisarRegistro(cliente a)throws Exception{
        abrirBanco();
        String query = "select nome_cliente, cpf, rg, celular, senha FROM cliente where nome_cliente=?";
        ps = (PreparedStatement) con.prepareStatement(query);
        ps.setString(1, a.getNome_cliente());
        ps.execute();
        rs = ps.executeQuery();
        
        if(rs.next()){
            a.setCpf(rs.getString("cpf"));
            a.setRg(rs.getString("rg"));
            a.setCelular(rs.getString("celular"));
            a.setSenha(rs.getString("senha"));
            
        }else{
            JOptionPane.showMessageDialog(null,"usuario não identificado");
        }
        fecharBanco();  
        
    }
    public void atualizar(cliente b) throws Exception{
        abrirBanco();
        String query = "UPDATE cliente SET nome_cliente=?,cpf=?,rg=?,celular=?,senha=? where nome_cliente=?" ;
        ps = (PreparedStatement) con.prepareStatement(query);
        ps.setString(1, b.getNome_cliente());
        ps.setString(2, b.getCpf());
        ps.setString(3, b.getRg());
        ps.setString(4, b.getCelular());
        ps.setString(5, b.getSenha());
        ps.setString(6, b.getNome_cliente());
        ps.execute();
        JOptionPane.showMessageDialog(null,"Cadastro atualizado com sucesso");
        fecharBanco();  
    }

    public void deletar(cliente d) throws Exception{
        abrirBanco();
        String query = "DELETE FROM cliente WHERE nome_cliente=?" ;
        ps = (PreparedStatement) con.prepareStatement(query);
        ps.setString(1, d.getNome_cliente());
        ps.execute();
        JOptionPane.showMessageDialog(null,"Cadastro EXCLUIDO com sucesso");
        fecharBanco();  
    }   
    
    
}

