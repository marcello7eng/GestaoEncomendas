package br.com.encomendas.dao;

import java.sql.*;
import java.util.*;
import br.com.encomendas.model.Encomenda;

public class EncomendaDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/encomendas_banco?useSSL=false&serverTimezone=UTC";
    private String jdbcUser = "root";
    private String jdbcPassword = "0000";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver JDBC não encontrado!", e);
        }
    }

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPassword);
    }

    public boolean salvar(Encomenda e) throws SQLException {
        String sql;
        boolean atualizar = e.getId() != null && e.getId() > 0;

        if (atualizar) {
            sql = "UPDATE encomendas SET codigo_rastreio = ?, link = ?, status = ?, descricao = ?, remetente = ?, observacoes = ? WHERE id = ?";
        } else {
            sql = "INSERT INTO encomendas (codigo_rastreio, link, status, descricao, remetente, observacoes) VALUES (?, ?, ?, ?, ?, ?)";
        }

        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, e.getCodigoRastreio());
            stmt.setString(2, e.getLink());
            stmt.setString(3, e.getStatus());
            stmt.setString(4, e.getDescricao());
            stmt.setString(5, e.getRemetente());
            stmt.setString(6, e.getObservacoes());

            if (atualizar) {
                stmt.setInt(7, e.getId()); // só no caso de update
            }

            int linhas = stmt.executeUpdate();
            return linhas > 0;
        }
    }

    public boolean excluir(Encomenda e) throws SQLException {
        String sql = "DELETE FROM encomendas WHERE id = ?";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, e.getId());
            int linhas = stmt.executeUpdate();
            return linhas > 0;
        }
    }

    public List<Encomenda> listar() throws SQLException {
        List<Encomenda> lista = new ArrayList<>();
        String sql = "SELECT id, codigo_rastreio, link, status, descricao, remetente, observacoes FROM encomendas";
        try (Connection conn = conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Encomenda e = new Encomenda();
                e.setId(rs.getInt("id"));
                e.setCodigoRastreio(rs.getString("codigo_rastreio"));
                e.setLink(rs.getString("link"));
                e.setStatus(rs.getString("status"));
                e.setDescricao(rs.getString("descricao"));
                e.setRemetente(rs.getString("remetente"));
                e.setObservacoes(rs.getString("observacoes"));
                lista.add(e);
            }
        }
        return lista;
    }
}