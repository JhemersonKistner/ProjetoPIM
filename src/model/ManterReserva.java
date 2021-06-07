/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Visao.TelaLogin;
import Visao.TelaMenuCliente;
import controle.reserva;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.util.logging.Level;
import java.util.logging.Logger;

/* `idreserva` int NOT NULL AUTO_INCREMENT,
  `nome_cliente` varchar(45) NOT NULL,
  `numquarto` int NOT NULL,
  `valor_diaria` int NOT NULL,
  `num_diarias` int NOT NULL,
  `statuss` varchar(45) NOT NULL,
  `valor_total` int GENERATED ALWAYS AS ((`valor_diaria` * `num_diarias`)) VIRTUAL,*/
/**
 *
 * @author Jhemerson
 */
public class ManterReserva extends DAO {
    String loginus;
    public void inserir(reserva u) throws Exception{
        abrirBanco();
        String query = "INSERT INTO reserva(idreserva,nome_cliente,numquarto,valor_diaria,num_diarias,statuss)values(null,?,?,?,?,?)";
        ps = (PreparedStatement) con.prepareStatement(query);
        ps.setString(1, u.getNome_cliente());
        ps.setInt(2, u.getNumquarto());
        ps.setInt(3, u.getValor_diaria());
        ps.setInt(4, u.getNum_diarias());
        ps.setString(5, u.getStatuss());
        ps.execute();
        JOptionPane.showMessageDialog(null,"reserva Feita com sucesso");
        fecharBanco();
    }

    
    public List<reserva> read(){
        List<reserva> reservas = new ArrayList<>();
        try {
            abrirBanco();
            String query = "select *from reserva";
            ps = (PreparedStatement) con.prepareStatement(query);
            ps.execute();
            rs = ps.executeQuery();
            
            
            
            while(rs.next()){
                reserva dados = new reserva();
                dados.setNome_cliente(rs.getString("nome_cliente"));
                dados.setNumquarto(rs.getInt("numquarto"));
                dados.setValor_diaria(rs.getInt("valor_diaria"));
                dados.setNum_diarias(rs.getInt("num_diarias"));
                dados.setStatuss(rs.getString("statuss"));
                dados.setValor_total(rs.getInt("valor_total"));
                reservas.add(dados);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManterReserva.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try { 
                fecharBanco();
            } catch (Exception ex) {
                Logger.getLogger(ManterReserva.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return reservas;
        
    }
    
    public void PesquisarRegistro(reserva a)throws Exception{
        abrirBanco();
        String query = "select idreserva,nome_cliente,numquarto,valor_diaria,num_diarias,statuss,valor_total FROM reserva where nome_cliente=?";
        ps = (PreparedStatement) con.prepareStatement(query);
        ps.setString(1, a.getNome_cliente());
        ps.execute();
        rs = ps.executeQuery();
        
        if(rs.next()){
                a.setNome_cliente(rs.getString("nome_cliente"));
                a.setNumquarto(rs.getInt("numquarto"));
                a.setValor_diaria(rs.getInt("valor_diaria"));
                a.setNum_diarias(rs.getInt("num_diarias"));
                a.setStatuss(rs.getString("statuss"));
                a.setValor_total(rs.getInt("valor_total"));
        }else{
            JOptionPane.showMessageDialog(null,"usuario não identificado");
        }
        fecharBanco();  
        
    }
    public void atualizar(reserva b) throws Exception{
        abrirBanco();
        String query = "UPDATE reserva SET nome_cliente=?,numquarto=?,valor_diaria=?,num_diarias=?,statuss=? where nome_cliente=?" ;
        ps = (PreparedStatement) con.prepareStatement(query);
        ps.setString(1, b.getNome_cliente());
        ps.setInt(2, b.getNumquarto());
        ps.setInt(3, b.getValor_diaria());
        ps.setInt(4, b.getNum_diarias());
        ps.setString(5, b.getStatuss());
        ps.setString(6, b.getNome_cliente());
        ps.execute();
        JOptionPane.showMessageDialog(null,"Reserva atualizada com sucesso");
        fecharBanco();  
    }

    public void deletar(reserva d) throws Exception{
        abrirBanco();
        String query = "DELETE FROM reserva WHERE nome_cliente=?" ;
        ps = (PreparedStatement) con.prepareStatement(query);
        ps.setString(1, d.getNome_cliente());
        ps.execute();
        JOptionPane.showMessageDialog(null,"Reserva EXCLUIDO com sucesso");
        fecharBanco();  
    }   
    
    
}

