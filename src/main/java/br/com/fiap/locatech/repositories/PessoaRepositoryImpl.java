package br.com.fiap.locatech.repositories;

import br.com.fiap.locatech.entities.Pessoa;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PessoaRepositoryImpl implements PessoaRepository {

    private final JdbcClient jdbcClient;

    public PessoaRepositoryImpl(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<Pessoa> findById(Long id) {
        return this.jdbcClient
                .sql("select * from pessoas where id = :id")
                .param("id", id)
                .query(Pessoa.class)
                .optional();
    }

    @Override
    public List<Pessoa> findAll(int size, int offset) {
        return this.jdbcClient
                .sql("select * from pessoas limit :size offset :offset ")
                .param("size", size)
                .param("offset", offset)
                .query(Pessoa.class)
                .list();
    }

    @Override
    public Integer save(Pessoa pessoa) {
        return this.jdbcClient
                .sql("insert into pessoas(nome, cpf, telefone, email) values (:nome, :cpf, :telefone, :email)")
                .param("nome", pessoa.getNome())
                .param("cpf", pessoa.getCpf())
                .param("telefone", pessoa.getTelefone())
                .param("email", pessoa.getEmail())
                .update();
    }

    @Override
    public Integer update(Pessoa pessoa, Long id) {
        return this.jdbcClient
                .sql("update pessoas set nome = :nome, cpf = :cpf, telefone = :telefone, email = :email where id = :id")
                .param("nome", pessoa.getNome())
                .param("cpf", pessoa.getCpf())
                .param("telefone", pessoa.getTelefone())
                .param("email", pessoa.getEmail())
                .param("id", id)
                .update();
    }

    @Override
    public Integer delete(Long id) {
        return this.jdbcClient
                .sql("delete from pessoas where id = :id")
                .param("id", id)
                .update();
    }
}
