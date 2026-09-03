import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

// Swing を使った GUI サンプルです。
// 1 つのウィンドウ（JFrame）の中で、CardLayout により
// 「入力画面」と「表示画面」の 2 つのパネルを切り替えます。
public class Main {

    // CardLayout でパネルを識別するための名前（カード名）です。
    private static final String CARD_INPUT = "input";
    private static final String CARD_RESULT = "result";

    // 入力欄の通常時の背景色（白）とエラー時の背景色（薄い赤）です。
    private static final Color NORMAL_COLOR = Color.WHITE;
    private static final Color ERROR_COLOR = new Color(255, 200, 200);

    public static void main(String[] args) {
        // Swing の画面部品は「イベントディスパッチスレッド」で操作するのが決まりです。
        SwingUtilities.invokeLater(Main::createAndShowGui);
    }

    // 画面を組み立てて表示します。
    private static void createAndShowGui() {
        JFrame frame = new JFrame("Hello World アプリ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 180);
        frame.setLocationRelativeTo(null); // ウィンドウを画面中央に表示します。

        // CardLayout を持つ「入れ物」のパネルを作り、そこに 2 画面を登録します。
        CardLayout cardLayout = new CardLayout();
        JPanel container = new JPanel(cardLayout);

        // ---- 入力画面（1 枚目のカード）----
        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel guideLabel = new JLabel("全角文字を入力してください（記号は不可）");
        JTextField textField = new JTextField(16);
        textField.setBackground(NORMAL_COLOR);
        JButton decideButton = new JButton("決定");
        JLabel errorLabel = new JLabel(" "); // エラーメッセージ表示用のラベルです。
        errorLabel.setForeground(Color.RED);
        inputPanel.add(guideLabel);
        inputPanel.add(textField);
        inputPanel.add(decideButton);
        inputPanel.add(errorLabel);

        // ---- 表示画面（2 枚目のカード）----
        JPanel resultPanel = new JPanel(new FlowLayout());
        JLabel resultLabel = new JLabel();
        JButton backButton = new JButton("戻る");
        resultPanel.add(resultLabel);
        resultPanel.add(backButton);

        container.add(inputPanel, CARD_INPUT);
        container.add(resultPanel, CARD_RESULT);

        // 「決定」ボタンが押されたときの処理です。
        decideButton.addActionListener(event -> {
            String text = textField.getText();
            if (isValidInput(text)) {
                // 入力チェック OK：メッセージを組み立てて表示画面へ切り替えます。
                textField.setBackground(NORMAL_COLOR);
                errorLabel.setText(" ");
                resultLabel.setText("Hello World! " + text);
                cardLayout.show(container, CARD_RESULT);
            } else {
                // 入力チェック NG：画面は切り替えず、入力欄を赤くします。
                textField.setBackground(ERROR_COLOR);
                errorLabel.setText("全角のひらがな・カタカナ・漢字・全角英数字のみ入力できます");
            }
        });

        // 「戻る」ボタンで入力画面へ戻ります。背景色は通常色に戻します。
        backButton.addActionListener(event -> {
            textField.setBackground(NORMAL_COLOR);
            errorLabel.setText(" ");
            cardLayout.show(container, CARD_INPUT);
        });

        frame.setContentPane(container);
        cardLayout.show(container, CARD_INPUT);
        frame.setVisible(true);
    }

    // 入力値が「全角のひらがな・カタカナ・漢字・全角英数字」だけで
    // 構成されているかどうかを判定します。
    // 空文字、半角文字、全角/半角の記号や空白が含まれる場合は false を返します。
    static boolean isValidInput(String text) {
        if (text == null || text.isEmpty()) {
            return false; // 空文字はエラーとします。
        }
        for (int i = 0; i < text.length(); i++) {
            if (!isAllowedChar(text.charAt(i))) {
                return false; // 許可されない文字が 1 つでもあればエラーです。
            }
        }
        return true;
    }

    // 1 文字が許可された範囲（Unicode のコードポイント）に含まれるかを判定します。
    private static boolean isAllowedChar(char c) {
        // ひらがな（U+3040〜U+309F）例: あ い う
        if (c >= '\u3040' && c <= '\u309F') {
            return true;
        }
        // カタカナ（U+30A0〜U+30FF）例: ア イ ウ
        if (c >= '\u30A0' && c <= '\u30FF') {
            return true;
        }
        // CJK 統合漢字（U+4E00〜U+9FFF）例: 漢 字
        if (c >= '\u4E00' && c <= '\u9FFF') {
            return true;
        }
        // 全角数字（U+FF10〜U+FF19）例: ０１２
        if (c >= '\uFF10' && c <= '\uFF19') {
            return true;
        }
        // 全角英字の大文字（U+FF21〜U+FF3A）例: ＡＢＣ
        if (c >= '\uFF21' && c <= '\uFF3A') {
            return true;
        }
        // 全角英字の小文字（U+FF41〜U+FF5A）例: ａｂｃ
        if (c >= '\uFF41' && c <= '\uFF5A') {
            return true;
        }
        // 上記以外（半角文字、全角記号 U+3000〜U+303F や U+FF01〜U+FF0F など）は
        // すべて許可しません。
        return false;
    }
}
