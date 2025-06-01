package Modelo.util;

import java.lang.reflect.Field;
import java.util.Set;
import org.reflections.ReflectionUtils;


public class SQLFormatoAtualizar implements SQLFormato {
    
    private Class<T> t;
    
    public SQLFormatoAtualizar(Class<T> t) {
       this.t = t;
    }
       
    
    
    
    @Override
    public String formato(){
        
                
       Set<Field> campos = ReflectionUtils.getFields(this.t, e -> true);
       StringBuilder atributos = new StringBuilder();
      
       
       
        try {
              for (Field campos : campos) {
              atributos.append(campos.getName()).append("=?,");
                       
            
        }
              
              String atributo =  removerUltimoCarater(atributo.toString());
              String SQL = String.format("UPDATE %s SET %s WHERE id = ?", t.getSimpleName(), atributo);
              
              return SQL;
                      
        } catch(Exception e) {
            throw new RuntimeException(e); 
        }        
    }
    
    private String removerUltimoCaracter(String texto){
        if (texto.isBlank()){
            return texto.substring(0, texto.length() -1);
        }
            throw new RuntimeException("Texto,vazio...");
        
    }
    
}
    

