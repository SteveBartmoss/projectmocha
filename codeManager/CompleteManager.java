public class CompleteManager{

    public String completeText(String texto){

        char letra = texto.charAt(cadena.length()-1);

        if(letra == '('){
            texto = texto + ')';
        }
        if(letra == '['){
            texto = texto + ']';
        }
        if(letra == '{'){
            texto = texto + '}';
        }

        return texto;
    }
    
}
