export default function SettingsModal() {
    return (
        <dialog open="" class="modal-overlay" data-testid="modal-overlay" aria-label="settings Modal" aria-modal="true" id="settings-dialog">
            <div class="modal-wrapper">
            <h2 class="modal-header">settings</h2>
            <button id="settings-close" class="modal-close" type="button" aria-label="Close">
                <svg aria-hidden="true" xmlns="http://www.w3.org/2000/svg" height="35" viewBox="0 0 24 24" width="20" class="game-icon" data-testid="icon-close"><path fill="var(--color-tone-1)" d="M19 6.41L17.59 5 12 10.59 6.41 5 5 6.41 10.59 12 5 17.59 6.41 19 12 13.41 17.59 19 19 17.59 13.41 12z"></path></svg>
            </button>
            <div class="modal-container">
                <section>  
                <div class="modal-menu-entry">
                    <div class="modal-menu-entry-title">
                    <h3>Dark Theme</h3>
                    </div>
                    <div class="modal-menu-switch-container">
                    <button id="theme-switch" aria-checked="false" aria-label="Dark Mode" role="switch" type="button" class="modal-menu-switch">
                        <span id="theme-switch-span" class="modal-menu-switch-knob"></span>
                    </button>
                    </div>
                </div>
                </section>
            </div>
            </div>
        </dialog>
    );
}