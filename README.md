# test_repo

Python のお試し(学習・検証)用プロジェクトです。最小限の構成から始められるようになっています。

## 必要なもの

- Python 3.8 以降

## ディレクトリ構成

```
.
├── README.md          # このファイル
├── .gitignore         # Git の除外設定
├── requirements.txt   # 依存パッケージの一覧(現時点では空)
└── src/
    └── main.py        # エントリーポイント
```

## 実行方法

リポジトリのルートで次のコマンドを実行します。

```bash
python src/main.py
```

出力:

```
Hello, World!
```

## 仮想環境の作り方(任意)

パッケージをプロジェクトごとに分けて管理したい場合は、仮想環境を使います。

```bash
python -m venv .venv
source .venv/bin/activate      # Windows の場合: .venv\Scripts\activate
pip install -r requirements.txt
```

## 次のステップ

- `src/main.py` を編集して、自分の処理を書いてみる
- 新しく使いたいライブラリを `requirements.txt` に追記し、`pip install -r requirements.txt` を実行する
- `src/` にファイルを追加してモジュールを分割してみる
