package Modelo.repositorio;


public interface CrudRepositorio<T> {
    
    public Boolean salvar(T t);
    
}
