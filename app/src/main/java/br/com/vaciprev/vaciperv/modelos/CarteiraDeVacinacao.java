package br.com.vaciprev.vaciperv.modelos;

import java.util.ArrayList;
import java.util.List;

public class CarteiraDeVacinacao {

    private List<Vacina> carteira;

    public CarteiraDeVacinacao(List<Vacina> vacinas) {
        carteira = vacinas;
    }

    public CarteiraDeVacinacao() {
        carteira = new ArrayList<Vacina>();
    }

    public Vacina addVacina(Vacina vacina){
        carteira.add(vacina);
        return vacina;
    }

    public Vacina getVacina(String vacina){
        Vacina vacinaEncontrada = null;

        for( Vacina v : carteira){
            if(v.getNome() != vacina){
                continue;
            }else{
                vacinaEncontrada = v;
            }
        }

        return vacinaEncontrada;
    }

//    public Vacina getVacina(String idVacina){
//
//        Vacina vacinaEncontrada = null;
//
//        for( Vacina v : carteira){
//            if(!v.getIdVacina().equalsIgnoreCase(idVacina) ){
//                continue;
//            }else{
//                vacinaEncontrada = v;
//            }
//        }
//
//        return vacinaEncontrada;
//    }

    public List<Vacina> getCarteira(){
        return this.carteira;
    }

    public boolean removeVacina(Vacina vacinaARemover){
        return carteira.remove(vacinaARemover);
    }

    @Override
    public String toString() {
        String texto = "[";

        for(Vacina v : carteira){
            texto += v.toString()+", ";
        }
        texto += "]";

        return texto;
    }

    public int size(){
        return carteira.size();
    }
}