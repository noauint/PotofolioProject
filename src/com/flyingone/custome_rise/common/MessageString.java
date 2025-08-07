/**
 * メッセージ文字列
 *
 * @author elder@example.com
 */
package com.flyingone.custome_rise.common;

/**
 * メッセージ文字列クラス
 * システム内で共通に使用するメッセージ文字列を定義する
 *
 * @author elder@example.com
 * @version 0.0.1
 */
public class MessageString {
	// システムメッセージ（System）
	public final static String MSG3 = "商品データを登録しました";
	public final static String MSG5 = "顧客データを登録しました";
	public final static String MSG6 = "売上データを登録しました";
	public final static String MSG7 = "商品データを削除しました";
	public final static String MSG8 ="顧客データを削除しました";
	public final static String MSG9 = "売上データを削除しました";
	public final static String MSG10 = "商品情報を更新しました";
	public final static String MSG11 = "顧客情報を更新しました"; // データベース接続（データベース接続パラメータの不備、データベース側の環境構築不備など）
	public final static String MSG12 = "売上情報を更新しました"; // SQL文の実行、パラメータ／結果セットの操作時のエラー

	// システムエラーメッセージ(System Error)
	public final static String errMsg1 = "ユーザーIDまたはパスワードが誤っています";
	public final static String errMsg2 = "商品データが取得できませんでした";
	public final static String errMsg3 = "登録済、または削除済の商品IDと重複しているため登録できませんでした";
	public final static String errMsg4 = "顧客データが取得できませんでした";
	//public final static String errMsg5 = "登録済、または削除済の顧客IDと重複しているため登録できませんでした";
	public final static String errMsg6 = "顧客名が登録できませんでした";
	public final static String errMsg7 = "売上データが取得できませんでした";
	public final static String errMsg8 = "商品IDは4桁で入力してください";
	public final static String errMsg9 = "売上データを登録できませんでした。数量は1～99ヶで入力してください";
	public final static String errMsg10  = "システムエラーが発生しました";
	public final static String errMsg11 = "売上データを更新できませんでした。数量は1～99ヶで入力してください";
	public final static String errMsg12= "単価・原価は９桁までで入力して下さい";
	public final static String errMsg13 = "単価・原価は整数で入力してください";
}
