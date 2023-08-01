package ifpr.paranavai.jogo.modelo;

import javax.swing.ImageIcon;

public class Tiro extends ElementoGrafico {
    private static final int VELOCIDADE = 5;

    public Tiro(int posicaoPersonagemEmX, int posicaoPersonagemEmY) {
        this.posicaoEmX = posicaoPersonagemEmX;
        this.posicaoEmY = posicaoPersonagemEmY;
    }

    @Override
    protected void carregar() {
        ImageIcon carregando = new ImageIcon("recursos\\tiro-nave-principal.gif");
        this.imagem = carregando.getImage();
        this.larguraImagem = this.imagem.getWidth(null);
        this.alturaImagem = this.imagem.getHeight(null);
    }

    @Override
    protected void atualizar() {
        this.posicaoEmX += VELOCIDADE;
    }
}
