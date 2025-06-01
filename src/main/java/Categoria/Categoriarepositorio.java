package Modelo.repositorio.implement;

import Modelo.entidade.Categoria;

public class Categoriarepositorio extends CrudRepositorioImpl {
    
    public Categoriarepositorio() {
        super(Categoria.class);
    }
    
    public static void main(String[] args) {
        Categoria categoria = Categoria.builder()
                .id(2L)
                .nome("Livro")
                .descricao("Conhecimento e poder")
                .build();
                
                Categoriarepositorio repositorio = new Categoriarepositorio();
                
                System.out.println("RESULTADO: " + repositorio.buscarPeloId(21));
    }
    
}
