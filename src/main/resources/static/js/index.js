/**
 * 初期画面
 */




/**
 * 初期表示
 */
window.addEventListener('DOMContentLoaded', () => {
	console.log('DOMContentLoaded');
});

/**
 * イベント登録用の要素取得
 */
const editBtns = document.getElementsByClassName('btn-edit');

/**
 * イベント登録
 */
// 編集ボタン

for(let editBtn of editBtns) {
	editBtn.addEventListener('click', async () => {
		console.log(editBtn.value);
	});
}
