
package projetosacadem;
import javax.swing.JOptionPane;
import java.util.ArrayList;
/**
 *
 * @author Vini_
 */
public class Matricula {
    private String cpfAluno;
    private String codigoDisciplina;
    ArrayList<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();
    
    public Matricula(String cpfAluno, String codigoDisciplina){
        this.cpfAluno = cpfAluno;
        this.codigoDisciplina = codigoDisciplina;
    }

    public String getCpfAluno() {
        return cpfAluno;
    }

    public void setCpfAluno(String cpfAluno) {
        this.cpfAluno = cpfAluno;
    }

    public String getCodigoDisciplina() {
        return codigoDisciplina;
    }

    public void setCodigoDisciplina(String codigoDisciplina) {
        this.codigoDisciplina = codigoDisciplina;
    }
    public boolean cadastraAvaliação(){
        String strNota = JOptionPane.showInputDialog(null, "Digite a nota da avaliação", "SACADEM", 1);
        double nota = Double.parseDouble(strNota);
        this.avaliacoes.add(new Avaliacao(nota));
        return true;
    }
    /*Esse metodo percorre a arraylist de avaliação e soma as avaliações
    na variavel soma depois verifica se a soma é maior que zero se for 
    ele calcula a media, depois salva esse valor na variavel String saida
    e retorna a saida*/
    public String calculaMedia(){
        String saida;
        double soma=0;
        double resultado=0;
        for(int i = 0; i < this.avaliacoes.size(); i++){
            soma += this.avaliacoes.get(i).getNotaDaProva();
        }
        if(soma > 0){
            resultado = soma / this.avaliacoes.size();
        }
        saida = "\nMedia:"+resultado+"\n=================================";
        return saida;
    }
    
}
