
package br.tatuapu.view;

import br.tatuapu.controller.Executor;
import br.tatuapu.model.Dados;
import br.tatuapu.model.Usuario;
import java.awt.BorderLayout;
import javax.swing.*;
import static br.tatuapu.util.Contexto.*;
import java.awt.event.*;
import java.util.ArrayList;
import br.tatuapu.view.*;

/**
 *
 * @author tatuapu
 */
public class TelaLogin extends JFrame{

    private final JTextField tUsuario;
    private final JPasswordField tSenha;
    private final JButton btnEntrar;
        
    public TelaLogin(){
        super(APLICACAONOME +" - "+APLICACAOVERSAO);
        JPanel painel = new JPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setSize(APLICACAOWP,APLICACAOHP);
        //centralizando
        setLocationRelativeTo(null);
        painel.add(new JLabel("Deseja entrar?"));                         
        painel.add(new JLabel("Digite seu usuário:"));
        tUsuario = new JTextField(15);
        painel.add(tUsuario);
        
        painel.add(new JLabel("Digite sua senha: "));
        tSenha = new JPasswordField(15);
        painel.add(tSenha);
        
        btnEntrar = new JButton("Entrar");
        painel.add(btnEntrar);
        
        setContentPane(painel);
        
        //ações
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrar();
            }
        });
    }
    protected void btnEntrar(){
        boolean checa = true;
        if(tUsuario.getText().equals("")){
            checa = false;
            tUsuario.requestFocus();
            JOptionPane.showMessageDialog(null, "Preencha corretamente o nome!");
        }else if(tSenha.getPassword().length<=0){
            checa = false;
            tSenha.requestFocus();
            JOptionPane.showMessageDialog(null, "Preencha corretamente a senha!");
        }
        if(checa){
            String n = tUsuario.getText();
            String p = new String(tSenha.getPassword());
            Dados dados = new Dados();
            dados.getUsuarios();
            Usuario u = dados.login(n, p);
            if(u==null){
                JOptionPane.showMessageDialog(null, "Dados Incorreto. Fechando o Programa!");
                System.exit(0);
            }else{
                JOptionPane.showMessageDialog(null, "Seja Bem-Vindo \n"+u.getNomeUsuario());
                this.dispose();
                Executor.setUsuarioLogado(u);
                Executor.abreTelaPrincipal();
            }
        }
    }
}
