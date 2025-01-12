package dao;

import java.sql.Connection;
import controller.conexao;
import model.clientes;
import model.usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class dao {

    private static final String CADASTRAR_CLIENTE = "INSERT INTO clientes (nome,cpfCnpj,email,telefone,endereco,rg,dataN,estadoC,profissao,cep,seguradora,apolice,item,placa,chassi,renavam,utilizacao,vigenciaF) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String CONSULTAR_CLIENTE = "SELECT * FROM clientes WHERE nome=?";
    private static final String ALTERAR_CLIENTE = "UPDATE clientes SET cpfCnpj=?,email=?,telefone=?,endereco=?,rg=?,dataN=?,estadoC=?,profissao=?,cep=?,seguradora=?,apolice=?,item=?,placa=?,chassi=?,renavam=?,utilizacao=?,vigenciaF=? WHERE nome=?";
    private static final String EXCLUIR_CLIENTE = "DELETE FROM clientes WHERE nome=?";
    private static final String LISTAR_CLIENTES = "SELECT * FROM clientes";
    private static final String CONSULTAR_USUARIO = "SELECT usuario, senha FROM usuario WHERE usuario=? and senha=?";

    public void cadastrarCliente(clientes cliente) {
        Connection connection = conexao.getConn().abrirConexao();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CADASTRAR_CLIENTE)) {
            int i = 1;
            preparedStatement.setString(i++, cliente.getNome());
            preparedStatement.setString(i++, cliente.getCpfCnpj());
            preparedStatement.setString(i++, cliente.getEmail());
            preparedStatement.setString(i++, cliente.getTelefone());
            preparedStatement.setString(i++, cliente.getEndereco());
            preparedStatement.setString(i++, cliente.getRg());
            preparedStatement.setString(i++, cliente.getDataN());
            preparedStatement.setString(i++, cliente.getEstadoC());
            preparedStatement.setString(i++, cliente.getProfissao());
            preparedStatement.setString(i++, cliente.getCep());
            preparedStatement.setString(i++, cliente.getSeguradora());
            preparedStatement.setString(i++, cliente.getApolice());
            preparedStatement.setString(i++, cliente.getItem());
            preparedStatement.setString(i++, cliente.getPlaca());
            preparedStatement.setString(i++, cliente.getChassi());
            preparedStatement.setString(i++, cliente.getRenavam());
            preparedStatement.setString(i++, cliente.getUtilizacao());
            preparedStatement.setString(i++, cliente.getVigenciaF());
            preparedStatement.execute();
            connection.commit();
            JOptionPane.showMessageDialog(null, "Cliente incluído com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(connection);
        }
    }

    public clientes consultarCliente(String nome) throws Exception {
        Connection connection = conexao.getConn().abrirConexao();
        clientes cliente = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(CONSULTAR_CLIENTE)) {
            preparedStatement.setString(1, nome);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    cliente = new clientes(
                        resultSet.getString("nome"),
                        resultSet.getString("cpfCnpj"),
                        resultSet.getString("email"),
                        resultSet.getString("telefone"),
                        resultSet.getString("endereco"),
                        resultSet.getString("rg"),
                        resultSet.getString("dataN"),
                        resultSet.getString("estadoC"),
                        resultSet.getString("profissao"),
                        resultSet.getString("cep"),
                        resultSet.getString("seguradora"),
                        resultSet.getString("apolice"),
                        resultSet.getString("item"),
                        resultSet.getString("placa"),
                        resultSet.getString("chassi"),
                        resultSet.getString("renavam"),
                        resultSet.getString("utilizacao"),
                        resultSet.getString("vigenciaF")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(connection);
        }
        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado.", "", JOptionPane.WARNING_MESSAGE);
            throw new Exception("Cliente não encontrado.");
        }
        return cliente;
    }

    public void alterarCliente(String nome, clientes cliente) throws Exception {
        Connection connection = conexao.getConn().abrirConexao();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ALTERAR_CLIENTE)) {
            int i = 1;
            preparedStatement.setString(i++, cliente.getCpfCnpj());
            preparedStatement.setString(i++, cliente.getEmail());
            preparedStatement.setString(i++, cliente.getTelefone());
            preparedStatement.setString(i++, cliente.getEndereco());
            preparedStatement.setString(i++, cliente.getRg());
            preparedStatement.setString(i++, cliente.getDataN());
            preparedStatement.setString(i++, cliente.getEstadoC());
            preparedStatement.setString(i++, cliente.getProfissao());
            preparedStatement.setString(i++, cliente.getCep());
            preparedStatement.setString(i++, cliente.getSeguradora());
            preparedStatement.setString(i++, cliente.getApolice());
            preparedStatement.setString(i++, cliente.getItem());
            preparedStatement.setString(i++, cliente.getPlaca());
            preparedStatement.setString(i++, cliente.getChassi());
            preparedStatement.setString(i++, cliente.getRenavam());
            preparedStatement.setString(i++, cliente.getUtilizacao());
            preparedStatement.setString(i++, cliente.getVigenciaF());
            preparedStatement.setString(i++, nome);
            preparedStatement.execute();
            connection.commit();
            JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(connection);
        }
    }

    public void excluirCliente(String nome) throws Exception {
        Connection connection = conexao.getConn().abrirConexao();
        try (PreparedStatement preparedStatement = connection.prepareStatement(EXCLUIR_CLIENTE)) {
            preparedStatement.setString(1, nome);
            preparedStatement.execute();
            connection.commit();
            JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(connection);
        }
    }

    public ArrayList<clientes> listarClientes() throws Exception {
        Connection connection = conexao.getConn().abrirConexao();
        ArrayList<clientes> listaClientes = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(LISTAR_CLIENTES);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                listaClientes.add(new clientes(
                    resultSet.getString("nome"),
                    resultSet.getString("cpfCnpj"),
                    resultSet.getString("email"),
                    resultSet.getString("telefone"),
                    resultSet.getString("endereco"),
                    resultSet.getString("rg"),
                    resultSet.getString("dataN"),
                    resultSet.getString("estadoC"),
                    resultSet.getString("profissao"),
                    resultSet.getString("cep"),
                    resultSet.getString("seguradora"),
                    resultSet.getString("apolice"),
                    resultSet.getString("item"),
                    resultSet.getString("placa"),
                    resultSet.getString("chassi"),
                    resultSet.getString("renavam"),
                    resultSet.getString("utilizacao"),
                    resultSet.getString("vigenciaF")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(connection);
        }
        if (listaClientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há clientes cadastrados.", "", JOptionPane.WARNING_MESSAGE);
        }
        return listaClientes;
    }

    private void fecharConexao(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
