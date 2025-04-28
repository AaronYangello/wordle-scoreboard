// components/LeaderboardRow.js
import '../styles/globals.css';

export default function LeaderboardRow({index}) {
    return (
        <div className={`leaderboard-row flex items-center gap-2 py-1 cursor-pointer transition-colors w-[90%] mx-auto flex-wrap opacity-0 translate-y-[50px] animate-fly-in`} style={{ animationDelay: `${index * 150}ms` }} >
            <button class="text-center p-3 cursor-pointer font-bold rounded-lg inline-block w-fit mx-auto my-2.5 hover:bg-gray-200 hover:scale-105 active:scale-95">
                Show More
            </button>
        </div>
    );
}
