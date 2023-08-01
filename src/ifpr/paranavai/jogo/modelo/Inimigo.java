package ifpr.paranavai.jogo.modelo;

import javax.swing.ImageIcon;

public class Inimigo extends ElementoGrafico {
    private static final int VELOCIDADE = 2;

    public Inimigo(int xAleatorio, int yAleatorio) {
        this.posicaoEmX = xAleatorio;
        this.posicaoEmY = yAleatorio;
    }

    @Override
    protected void carregar() {
        ImageIcon carregando = new ImageIcon("recursos\\nave-inimiga.png");
        this.imagem = carregando.getImage();
        this.larguraImagem = this.imagem.getWidth(null);
        this.alturaImagem = this.imagem.getHeight(null);
    }

    @Override
    protected void atualizar() {
        this.posicaoEmX -= VELOCIDADE;
    }
}
