// components/LeaderboardRow.js
import '../styles/globals.css';
import { useEffect, useRef, useState } from 'react';

export default function LeaderboardRow({ user, index, is_current_user = false }) {
    const rowRef = useRef(null);
    const [detailsVisible, setDetailsVisible] = useState(false);

    // Toggle details on row click
    const toggleDetails = () => setDetailsVisible(prev => !prev);
    const getRandomColor = () => {
        const colors = ['gray', 'green', 'yellow'];
        return colors[Math.floor(Math.random() * colors.length)];
    };

    return (
        <div className={`leaderboard-row flex items-center gap-2 py-1 cursor-pointer transition-colors w-[90%] mx-auto flex-wrap opacity-0 translate-y-[50px] animate-fly-in ${is_current_user ? 'current-user' : ''}`} style={{ animationDelay: `${index * 150}ms` }} onClick={toggleDetails} >
            <div class="leaderboard-row-contents">
                <div className="dropdown-icon">
                    <svg className={detailsVisible ? 'open' : ''} version="1.0" xmlns="http://www.w3.org/2000/svg" width="12" height="12" viewBox="0 0 512.000000 512.000000" preserveAspectRatio="xMidYMid meet">
                    <g transform="translate(0.000000,512.000000) scale(0.100000,-0.100000)" fill="var(--color-tone-1)" stroke="none">
                        <path d="M787 3616 c-86 -31 -136 -96 -145 -187 -9 -99 -48 -55 902 -1007 527 -529 897 -892 921 -905 53 -28 135 -28 190 1 48 25 1766 1742 1788 1787 53 107 8 238 -97 286 -51 23 -136 24 -179 3 -18 -9 -386 -370 -819 -802 l-788 -787 -802 802 c-693 691 -808 803 -843 812 -52 14 -81 13 -128 -3z" />
                    </g>
                    </svg>
                </div>
                <div class="rank">{user.rank}</div>
                <div class="username">
                    {user.name.split('').map((letter, i) => (
                        <span key={i} className={`wordle-box ${getRandomColor()}`}>
                            {letter}
                        </span>
                    ))}
                </div>
                <div class="score">
                    {user.score.split('').map((digit, i) => (
                        <span key={i} className={`wordle-box ${getRandomColor()}`}>
                            {digit}
                        </span>
                    ))}
                </div>
            </div>
            {detailsVisible && (
                <div className="details" style={{ animation: 'flyInFromTop 0.3s ease-out' }}>
                    <span data-col="1">Current Streak: {user.current_streak}</span>
                    <span data-col="1">Longest Streak: {user.longest_streak}</span>
                    <span data-col="1">Active for: {user.active_for}</span>
                    <span data-col="2">Highest Single Game Score: {user.highest_score}</span>
                    <span data-col="2">Average Single Game Score: {user.average_score}</span>
                    <span data-col="2">Scores Entered: {user.scores_entered}</span>
                    <span data-col="3">Times in the Top 6: {user.times_in_top_six}</span>
                    <span data-col="3">Times at #1: {user.times_at_num_one}</span>
                    <span data-col="3">Followers: {user.followers}</span>
                </div>
            )}
        </div>
    );
}
