package Modelo.repositorio.implement;

import Modelo.conexao.ConexaoMySQL;
import Modelo.util.SQLFormato;
import Modelo.util.SQLFormatoInserir;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import org.reflections.ReflectionUtils;

public abstract class CrudRepositorioImpl<T> implements CrudRepositorio<T>{
    
    private final Class<T> t;

    public CrudRepositorioImpl(Class<T> t) {
        this.t = t;
    }

    @Override
    public boolean salvar(T t) {
        Object id = null;
        Set<Field> campos = ReflectionUtils.getFields(this.t, e -> true);

try {
            for (Field campo : campos) {
                campo.setAccessible(true);
                if (campo.getName().equalsIgnoreCase("id")) {
                    id = campo.get(t);
                }
            }

            if (id == null) {
                 SQLFormato sql = new SQLFormatoInserir(this.t);
                 return persistencia(sql.formato(), t, false);
            }

            SQLFormato sql = new SQLFormatoAtualiza(this.t);
            return persistencia(sql.formato(), t, true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    
    private boolean persistencia(String sql, Object t, boolean atualiza){
        try {
            PreparedStatement ps = ConexaoMySQL.obterConexao().prepareStatement(sql);
            
            preencherPreparedStatement(t, ps, atualiza);
            
            int resultado = ps.executeUpdate();
   return resultado == 1;
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    private void preencherPreparedStatement(Object t, PreparedStatement ps, boolean atualiza) {
        int contador = 1;
        Set<Field> campos = ReflectionUtils.getFields(this.t, e -> true);

        try {
            for (Field campo : campos) {
                campo.setAccessible(true);
                if (atualiza && campo.getName().equalsIgnoreCase("id")) {
                    ps.setObject(campos.size(), campo.get(t));
                    continue;
                }
                
                ps.setObject(contador, campo.get(t));
                contador++;
            }
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

   @Override
   public List<T> buscarTodos() {
       List<T> list = new ArrayList<>();
       try {
           String SQL = String.format("SELECT * FROM %s", t.getSimplName());
           ResultSet resultSet = ConexaoMySQL.obterConexao().prepareStatement(SQL);
                   .executeQuery();
                   
                   while (resultSet.next()) {
                       lista.add(getT(resultSet)) ;                      
                   }
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
       return lista;
   }
   private T getT(ResultSet resultSet) {
       try {
            T tNovo = t.newInstance();
            Method metodo = null;
            Set<Field> campos = ReflectionUtils.getAllFields(t, e -> true);
            
            for (Field campo : campos){
                Object valor = null;
                String nome = campo.getName();
                
                switch(campo.getType().getSimpleName().toUpperCase()) {
                    case "STRING" -> { 
                    valor = resultSet.getString(nome);
                    metodo = t.getMethod("set" + primeiraLetraMaiuscula(nome), String.calss)
                    }
                    
                    case "LONG" -> {
                        valor = resultSet.getLong(nome);
                        metpdp = t.getMethod("set" + primeiraLetraMaiuscula(nome), Long.calss)
                    }
                       case "INTEGER" -> {
                        valor = resultSet.getInt(nome);
                        metpdp = t.getMethod("set" + primeiraLetraMaiuscula(nome), Integer.calss)
                    }
                       
                       case "BOOLEAN" -> {
                        valor = resultSet.getBoolean(nome);
                        metpdp = t.getMethod("set" + primeiraLetraMaiuscula(nome), Boolean.calss)
                    }
                       
                       case "OBJECT" -> {
                        valor = resultSet.getObject(nome);
                        metpdp = t.getMethod("set" + primeiraLetraMaiuscula(nome), Object.calss)
                    }
                       case "LOCALDATETIME" -> {
                        valor = resultSet.getObject(nome, LocalDateTime.class);
                        metpdp = t.getMethod("set" + primeiraLetraMaiuscula(nome), LocalDateTime.calss)
                    }
                       
                        case "BIGDECIMAL" -> {
                        valor = resultSet.getBigDecimal(nome);
                        metpdp = t.getMethod("set" + primeiraLetraMaiuscula(nome), BigDecimal.calss)
                    }
                       
                }
                
                metodo.invoke(tNovo, valor);
            }
            
            return tNovo;
       } catch (Exception e) {
         throw new RuntimeException(e);
       }
   }
   
   private String primeiraLetraMaisucula(String texto) {
       if (texto.isBlank()) {
           return text.substring(0, 1).toUpperCase().concat(texto.substring(1));
       }
       
       return "";
   }
   
   
   @Ovrride
   public Optional<T> buscarPeloId(Long id) {
       try {
           String SQL = String.fomat("SELECT * FROM %s WHERE id = ?", t.getSimpleName());
           
           PreparedStatement ps = ConexaoMySQL.obterConexao().prepareStatement(SQL);
           ps.setLong(1, id);
           
           ResultSet resultset = ps.executeQuery();
           if (resultSet.next()) {
               Optional.ofNullable(getT(resultSet));               
           }
       } catch (Exception e) {
           trow new RuntimeException(e);
       }
       
        return Optionsl.empty();
   }
   @Override 
   public boolean removerPeloId(Long id) {
        try {
           String SQL = String.fomat("DELETE FROM %s WHERE id = ?", t.getSimpleName());
           System.out.print("SQL" + SQL);
           
           PreparedStatement ps = ConexaoMySQL.obterConexao().prepareStatement(SQL);
           ps.setLong(1, id);
          
           int resultado = ps.executeUpdate();
           
           return resultado == 1;
       } catch (Exception e) {
           trow new RuntimeException(e);
       }
    }
  
}