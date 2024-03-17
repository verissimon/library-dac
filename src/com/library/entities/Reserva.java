package com.library.entities;

import java.time.LocalDate;

public class Reserva {
    
    private Livro livro;
    private Aluno aluno;
    private LocalDate dataReserva;
    private LocalDate dataDevolucao;

    public Reserva(Aluno aluno, Livro livro, LocalDate dataReserva, LocalDate dataDevolucao) {
        this.aluno = aluno;
        this.livro = livro;
        this.dataReserva = dataReserva;
        this.dataDevolucao = dataDevolucao;
    }

    public Reserva() {
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public LocalDate getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(LocalDate dataReserva) {
        this.dataReserva = dataReserva;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "livro=" + livro.getTitulo() +
                ", aluno=" + aluno.getNomeCompleto() +
                ", dataReserva=" + dataReserva +
                ", dataDevolucao=" + dataDevolucao +
                '}';
    }

}
