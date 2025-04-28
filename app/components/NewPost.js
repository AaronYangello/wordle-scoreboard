export default function NewPost() {
    const currentWordle = 1339; // Simulating current Wordle number
    const placeholderText = `Paste your Wordle score here and see how you stack up!\nWordle ${currentWordle} 6/6\n\n\n⬛⬛🟩🟨⬛\n⬛🟨🟩🟩⬛\n🟩⬛🟩🟩⬛\n🟩🟨🟩🟩⬛\n🟩⬛🟩🟩🟩\n🟩🟩🟩🟩🟩`;
  return (
    <div class="new-post">
        <label for="post-text">Post Your Wordle Score</label>
        <div id="countdown" class="countdown-container"></div>
        <textarea rows="10" placeholder={placeholderText}></textarea>
        <button class="post-btn" type="button">Post</button>
      </div>
  );
}