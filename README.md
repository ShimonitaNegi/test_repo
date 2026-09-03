# test_repo

Java のお試し用プロジェクトです。ビルドツール（Maven / Gradle）を使わない最小構成で、
Hello World を動かしながら Java の基本を確認できます。

## ディレクトリ構成

```
.
├── README.md
├── .gitignore
└── src
    └── Main.java   # Hello World を出力するプログラム
```

## 必要なもの

- JDK 11 以上（`java -version` と `javac -version` で確認できます）

## 実行方法

### 1. コンパイルしてから実行する（基本の手順）

```bash
javac src/Main.java   # コンパイル（src/Main.class が生成されます）
java -cp src Main     # 実行
```

出力:

```
Hello, World!
```

`-cp src` は「クラスファイルを探す場所（クラスパス）に src を指定する」という意味です。

### 2. コンパイルせずに直接実行する（Java 11 以上）

Java 11 以上であれば、1 ファイルのプログラムは次のように直接実行できます。

```bash
java src/Main.java
```

この場合 `.class` ファイルは生成されません。手軽に試したいときに便利です。

## 次のステップ

- `System.out.println` の文字列を変えて、出力が変わることを確認する
- `main` メソッドの `args`（コマンドライン引数）を表示してみる
- 新しいクラスを `src` に追加して、`Main` から呼び出してみる
- 慣れてきたら Maven や Gradle などのビルドツールを導入してみる
