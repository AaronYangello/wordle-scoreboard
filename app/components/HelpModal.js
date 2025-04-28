export default function HelpModal() {
    return (
        <dialog open="" class="modal-overlay" data-testid="modal-overlay" aria-label="help Modal" aria-modal="true" id="help-dialog">
        <div class="modal-wrapper">
        <h2 class="modal-header">How to Use the Score Board</h2>
        <button id="help-close" class="modal-close" type="button" aria-label="Close">
            <svg aria-hidden="true" xmlns="http://www.w3.org/2000/svg" height="35" viewBox="0 0 24 24" width="20" class="game-icon" data-testid="icon-close"><path fill="var(--color-tone-1)" d="M19 6.41L17.59 5 12 10.59 6.41 5 5 6.41 10.59 12 5 17.59 6.41 19 12 13.41 17.59 19 19 17.59 13.41 12z"></path></svg>
        </button>
        <div class="modal-container">
            <section>  
            <div class="modal-menu-entry">
                <p>
                Welcome to the World Score Board! Use the toggle at the top to switch between viewing all users or just those you follow.
                Submit your Wordle score using the form above to share your experience.
                Click on any leaderboard entry to view more details about that user.
                Use the gear icon to switch between light and dark themes.
                </p>
            </div>
            </section>
        </div>
        </div>
    </dialog>
    );
}