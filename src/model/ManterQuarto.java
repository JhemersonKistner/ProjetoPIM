/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controle.quarto;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Weisse
 */
public class ManterQuarto extends DAO{
    
    public List<quarto> read(){
        List<quarto> quartos = new ArrayList<>();
        try {
            abrirBanco();
            String query = "select *from quarto";
            ps = (PreparedStatement) con.prepareStatement(query);
            ps.execute();
            rs = ps.executeQuery();
            
            
            
            while(rs.next()){
                quarto dados = new quarto();
                dados.setNumquarto(rs.getInt("numquarto"));
                dados.setAndar_quarto(rs.getInt("andar_quarto"));
                dados.setNum_camas(rs.getInt("num_camas"));
                dados.setVaranda(rs.getString("varanda"));
                quartos.add(dados);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManterQuarto.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try { 
                fecharBanco();
            } catch (Exception ex) {
                Logger.getLogger(ManterQuarto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return quartos;
        
    }
    
    public void PesquisarRegistro(quarto a)throws Exception{
        abrirBanco();
        String query = "select numquarto, andar_quarto, num_camas, varanda FROM quarto where varanda=?";
        ps = (PreparedStatement) con.prepareStatement(query);
        ps.setString(1, a.getVaranda());
        ps.execute();
        rs = ps.executeQuery();
        
        if(rs.next()){
            a.setNumquarto(rs.getInt("numquarto"));
            a.setAndar_quarto(rs.getInt("andar_quarto"));
            a.setNum_camas(rs.getInt("num_camas"));
            a.setVaranda(rs.getString("varanda"));
            
        }else{
            JOptionPane.showMessageDialog(null,"Quarto não encontrado");
        }
        
    }
    
    
}

