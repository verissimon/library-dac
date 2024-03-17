package com.library.entities;

import java.time.LocalDate;

/*
*   
    import java.time.format.DateTimeFormatter;

    DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

    LocalDate date = LocalDate.now(); // data de nova reserva
    String textDate = date.format(formatter); // LocalDate -> String para salvar no banco
    LocalDate parsedDate = LocalDate.parse(textDate, formatter); // String textDate -> LocalDate para recuperar do banco
    System.out.println(textDate + " " + parsedDate);
 
 */

public class Reserva {
    
    private Livro livro;
    private Aluno aluno;
    private LocalDate dataReserva;
    private LocalDate dataDevolucao;

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
