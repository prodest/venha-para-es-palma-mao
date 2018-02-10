/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISAO;

import CONTROLE.DAO.VerificaStatusDAO;
import static CONTROLE.DAO.VerificaStatusDAO.ResetConfigs;
import static CONTROLE.DAO.VerificaStatusDAO.TheConfIsValid;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author mgarcia
 */
public class AppStart {

    //este boolean sera manipulado pela janela de configuração do .conf
    public static String candidatostxt;
    public static String concursostxt;
    //paths para os arquivos ^

    //padrão do NetBeans levemente customizado para facilitar a leitura
    //este método foi retirado do main padrão dos JFrames que o NetBeans gera
    //sua serventia é deixar o visual dos JFrames mais bonitos.
    public static void setNimbusVisual() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ConfiguraConf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConfiguraConf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConfiguraConf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConfiguraConf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    }

    //a mágica começa aqui
    public static void main(String[] args) {
        //inicia com aquele padrão do NetBeans enxugado
        setNimbusVisual();
        

        /*
        *   ************************* PASSO 1 *******************************
        *   VOCÊ DEVE CONFIGURAR O SERVIDOR MYSQL COM O SCRIPT CREATE.sql
        *   QUE ACOMPANHA ESTE PROJETO NA PASTA BancoDeDados.
        *   AO PREPARAR O SERVIDOR, ANOTE O USUARIO, SENHA IP E PORTA,
        *   O PROGRAMA SOLICITARÁ AS INFORMAÇÕES CASO NÃO ESTEJAM CONFIGURADAS
        *   NO ARQUIVO mysql.conf QUE ESTÁ NA PASTA RAIZ DO PROJETO
        *   *****************************************************************
        */
        
        
        if (false == TheConfIsValid()) {
            JOptionPane.showMessageDialog(null, "Antes de rodar a aplicação, crie o "
                    + "Banco de dados em um servidor MySQL \nusando o script "
                    + "CREATE.sql que acompanha este Projeto na pasta "
                    + "'BancoDeDados'\nAnote a porta do servidor, o usuario e a "
                    + "senha. A aplicação irá solicitar esses dados a seguir");
            
            

        /*
        *   ************************ PASSO 2 ********************************
        *   APÓS TER CONFIGURADO O SERVIDOR MYSQL COM O SCRIPT CREATE.sql
        *   QUE ACOMPANHA O PROJETO NA PASTA BancoDeDados, A APLICAÇÃO IRÁ
        *   VERIFICAR SE O ARQUIVO DE CONFIGURAÇÃO DO BANCO, O mysql.conf,
        *   QUE ESTÁ NA PASTA RAIZ DO PROJETO, ESTÁ DEVIDAMENTE CONFIGURADO.
        *   NA PRIMEIRA EXECUÇÃO ELE NÃO ESTARÁ, ENTÃO VOCÊ DEVERÁ INFORMAR
        *   OS DADOS ANOTADOS NO PASSO 1 AO FORMULARIO QUE SERÁ EXIBIDO.
        *   A APLICAÇÃO SE ENCARREGA DE CONFIGURAR O ARQUIVO SEM INTERVENÇÃO
        *   MANUAL NO MESMO.
        *   *****************************************************************
        */
        
        
        
            if (false == TheConfIsValid()) {
                JOptionPane.showMessageDialog(null, "Parece que seu arquivo de "
                        + "configuração do banco de dados ainda não está bem "
                        + "configurado. Vamos resolver isso");
                new ConfiguraConf(null, true).setVisible(true);
            }
        }

        
        
            /*
            *   *************************** PASSO 3 *************************
            *   SE O FLUXO CHEGAR NESTE PONTO, O ARQUIVO mysql.conf ESTÁ
            *   DEVIDAMENTE CONFIGURADO(espero que sim :-)). AGORA A 
            *   APLICAÇÃO IRÁ VERIFICAR A CONEXÃO COM O BANCO DE DADOS E 
            *   CHECAR SE OS ARQUIVOS JÁ FORAM PROCESSADOS ANTERIORMENTE.
            *   CASO A LÓGICA CONCLUA QUE O BANCO JÁ EXISTE, QUE O SCHEMA ESTÁ 
            *   EM ORDEM E QUE OS ARQUIVOS JÁ FORAM PROCESSADOS, ESTA ETAPA
            *   SERÁ SUMARIAMENTE IGNORADA E A APLICAÇÃO IRÁ ABRIR A TELA
            *   PRINCIPAL, ONDE A MISSÃO SERÁ CUMPRIDA COM O ÊXITO ESPERADO.
            *   CASO CONTRARIO, A APLICAÇÃO IRÁ TENTAR CONFIGURAR O AMBIENTE
            *   COM AUXILIO DO OPERADOR DO SISTEMA OU USUÁRIO.
            *   *************************************************************
            */
            
            
        // LÓGICA PARA VERIFICAR O AMBIENTE
        boolean bancoexiste = VerificaStatusDAO.BancoExiste();
        boolean arquivosprocessados = false;
        if (TheConfIsValid()) {
            if (!bancoexiste) {
                JOptionPane.showMessageDialog(null, "O Banco de Dados não foi "
                        + "encontrado!\nVerifique se você digitou corretamente "
                        + "todas as informações na janela de configuração do "
                        + "banco de dados,\nSe ainda não executou, execute o "
                        + "script CREATE.sql no Servidor do  MySQL.\n"
                        + "Certifique-se que seu usuario"
                        + " possui as permissões adequadas para criar schemas"
                        + " e tabelas.\nVerifique o arquivo de configuração "
                        + "mysql.conf na pasta raiz da aplicação");
                ResetConfigs(); //reseta o mysql.conf
                System.exit(0);
            }
            if (bancoexiste) {
                //o banco existe e está configurado
                try {
                    arquivosprocessados = VerificaStatusDAO.ArquivosProcessados();
                    // agora x informa se os arquivos estão processados ou nao
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro ao checar os dados" + e);
                    System.exit(0);
                }
            }
            
            

            /*
            * *************************** PASSO 4 *************************
            *   NESTA ETAPA, APÓS CONFIRMAR QUE O BANCO EXISTE E ESTÁ 
            *   CONFIGURADO COM O SCHEMA Concursos E POSSUI AS 5 TABELAS,
            *   E APÓS VERIFICAR QUE OS ARQUIVOS AINDA NÃO FORAM PROCESSADOS,
            *   O PROGRAMA IRÁ ABRIR O SELETOR DE ARQUIVOS ONDE O OPERADOR
            *   IRÁ APONTAR PARA OS ARQUIVOS candidatos.txt E concursos.txt
            *   APÓS APONTAR PARA OS ARQUIVOS, O SELETOR IRÁ DIRECIONAR O
            *   FLUXO PARA OS DEVIDOS BACKENDS, ONDE A MÁGICA ACONTECERÁ.
            *   ************************************************************
            */
            
            
            
            if (bancoexiste && !arquivosprocessados) {
                //os arquivos não estão processados. processe!
                new SeletorDeArquivos(null, true).setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(null, "O arquivo de configuração "
                    + "mysql.inf é invalido");
            System.exit(0);
        }
        
        

        /*
        *   *************************** PASSO 5 *************************
        *   APÓS O BANCO ESTAR ONLINE, CONFIGURADO, COM OS DADOS DOS
        *   ARQUIVOS JÁ PROCESSADOS E ARMAZENADOS LA DENTRO, O PROGRAMA
        *   ENTRARÁ EM SEU PRINCIPAL ESTÁGIO: A TELA PRINCIPAL SERÁ 
        *   EXIBIDA E O OPERADOR PODERÁ FAZER AS DUAS CONSULTAS QUE
        *   O TESTE DA PRODEST SOLICITOU! THAT'S IT! GG WP!
        *   ************************************************************
        */
        
        
        
        if (bancoexiste && arquivosprocessados) {
            new TelaDeConsultas().setVisible(true);
        }

        //PRONTO!
        //MATEUS GARCIA LOPES
        //INSCRIÇÃO 750838
    }
}
