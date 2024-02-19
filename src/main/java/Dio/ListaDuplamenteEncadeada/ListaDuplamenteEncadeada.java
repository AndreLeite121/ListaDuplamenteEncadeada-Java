package Dio.ListaDuplamenteEncadeada;

public class ListaDuplamenteEncadeada<T> {
    private NoDuplo<T> primeiroNo;
    private NoDuplo<T> ultimoNo;

    private int TamanhoLista = 0;



    public void add(T elemento){
        NoDuplo<T> novoNo = new NoDuplo<T>(elemento);// Criamos um novo nó.
        novoNo.setNoProximo(null); // setamos que ele é o ultimo nó.
        novoNo.setNoPrevio(ultimoNo); // setamos que o nó anterior era o ultimo.
        if (primeiroNo == null){//Se o primeiro é nulo ele ira receber um novo nó.
            primeiroNo = novoNo;
        }
        if (ultimoNo != null){// Se o ultimo nó nao for nulo ele ira receber o valor do nó depois dele.
            ultimoNo.setNoProximo(novoNo);
        }
        ultimoNo = novoNo;// se algum deles forem nulos, quer dizer que esta no final da lista e é só incrementar o novo nó.
        TamanhoLista++;// Vamos incrementar o contator para o tamanho da lista.
    }

    public void add(int index, T elemento){ // Funcao para adicionar um no, ou meio, ou no fim, ou no inicio.
        NoDuplo<T> noAuxiliar = getNo(index); // Criamos uma variavel para armazenar o indice do nó que escolhemos.
        NoDuplo<T> novoNo = new NoDuplo<>(elemento); // Criamos uma variavel para armazenar o nó que temos.
        novoNo.setNoProximo(noAuxiliar);//setamos que o proximo nó ao novo que vamos colocar é o auxiliar.

        if (novoNo.getNoProximo() != null){//Se o nó do indice que escolhemos for diferente de nulo.
            novoNo.setNoPrevio(noAuxiliar.getNoPrevio());// O no anterior vai se ligar ao novo no que escolhemos.
            novoNo.getNoProximo().setNoPrevio(novoNo);// E o proximo nó vai se ligar a ele tambem. Assim inserindo ele no meio dos dois.
        }else {
            novoNo.setNoPrevio(ultimoNo);// Se o nó for nulo então ele é o ultimo, com isso o anterior passa a ser o ultimo.
            ultimoNo = novoNo;//E o que era ultimo passa a ser o novo nó.
        }
        if (index == 0){// Se escolhermos o indice 0.
            primeiroNo = novoNo; // O primeiro nó passa a ser o novo nó que escolhemos.
        }else {
            novoNo.getNoPrevio().setNoProximo(novoNo);// O novo nó que escolhemos passa a ser o proximo nó do primeiro, ou seja , o segundo nó.
        }

        TamanhoLista++;// Vamos incrementar o contator para o tamanho da lista.

    }

    public T get(int index){// Funcao para exibir o conteudo do nó.
        return this.getNo(index).getConteudo();// Retorna o conteudo do nó solicitado.
    }

    private NoDuplo<T> getNo(int index){ //Funcao para percorrer a lista.
        NoDuplo<T> noAuxiliar = primeiroNo;// Criamos um no auxiliar para receber o primeiro nó.
        for (int i=0; (i < index) && (noAuxiliar != null); i++){ // Um for para percorrer a lista com duas limitacoes.
            noAuxiliar = noAuxiliar.getNoProximo();// um no rebendo o proximo para fazer a lista andar
        }
        return noAuxiliar;// retorna o nó auxiliar.
    }

    public void remove(int index){//Criamos uma funcao para excluir um determinado indice.
        if(index == 0){// Se o indice for zero, ou seja o primeiro nó.
            primeiroNo = primeiroNo.getNoProximo();// O primeiro nó ira receber o conteudo do proximo no, no caso o 1.
            if (primeiroNo != null){// O primeiro nó sendo diferente nulo.
                primeiroNo.setNoPrevio(null);// O primeiro no será removido.
            }
        }else{// Se o indice não for zero.
            NoDuplo<T> noAuxiliar = getNo(index);//Criamos uma varivavel que recebe o nó do indice
            noAuxiliar.getNoPrevio().setNoProximo(noAuxiliar.getNoProximo());//Agora vamos quebrar esse nó fazendo com que o seu no anterior se ligue ao seu nó proximo.
            if (noAuxiliar != ultimoNo){//Se o nó nao for o ultimo
                noAuxiliar.getNoProximo().setNoPrevio(noAuxiliar.getNoPrevio());// Agora foi quebrado da mesma forma q antes, só que agora o proximo nó se liga ao no anterior.
            }else {
                ultimoNo = noAuxiliar;// Se o nó escolhido for o ultimo ele só recebe o ultimo e é desconectado.
            }
        }

        this.TamanhoLista--;//Vamos decrementar o contator para o tamanho da lista.
    }

    public int Size(){//Funcao para sabermos qual o tamanho da lista
        return TamanhoLista;
    }

    @Override
    public String toString() {
        String strRetorno = "";
        NoDuplo<T> noAuxiliar = primeiroNo;

        for (int i=0; i < Size(); i++){
            strRetorno += "[No{conteudo =" + noAuxiliar.getConteudo() + "}]--->";
            noAuxiliar = noAuxiliar.getNoProximo();
        }
        strRetorno += "null";
        return strRetorno;
    }
}
