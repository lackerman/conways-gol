package io.lacker.render;

import io.lacker.classifier.StateClassifier;

import javax.swing.*;
import java.awt.*;

public class GraphicsRenderer<T> extends Renderer<T> {

  private static final int SIZE = 10;

  private final JPanel panel;
  private final Runnable onShutdown;
  private T[][] board;

  public GraphicsRenderer(StateClassifier<T> stateClassifier, int numRows, int numColumns, Runnable onShutdown) {
    super(stateClassifier, numRows, numColumns);
    this.panel = new ConwayPanel();
    this.onShutdown = onShutdown;
  }

  @Override
  public void render(T[][] board) {
    this.board = board.clone();
    SwingUtilities.invokeLater(panel::updateUI);
  }

  @Override
  public void shutdown() {
    onShutdown.run();
  }

  public JPanel getPanel() {
    return panel;
  }

  private final class ConwayPanel extends JPanel {

    public void paintComponent(Graphics graphics) {
      graphics.setColor(Color.WHITE);
      graphics.fillRect(0, 0, SIZE * getNumColumns(), SIZE * getNumRows());

      graphics.setColor(Color.BLACK);
      for (int row = 0; row < getNumRows(); row++) {
        for (int col = 0; col < getNumColumns(); col++) {
          if (getStateClassifier().isAlive(board[row][col])) {
            graphics.fillRect(col * SIZE, row * SIZE, SIZE, SIZE);
          }
        }
      }
    }
  }
}
