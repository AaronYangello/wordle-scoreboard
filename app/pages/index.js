// pages/index.js
import Head from 'next/head';
import Header from '../components/Header';
import Leaderboard from '../components/Leaderboard';
import Newsfeed from '../components/Newsfeed';
import Footer from '../components/Footer';

export default function Home() {
  return (
    <div>
      <Head>
        <title>Wordle Scoreboard</title>
        <link rel="icon" href="https://www.nytimes.com/games-assets/v2/metadata/wordle-favicon.ico?v=v2502061202" />
      </Head>
      <Header />
      <main className="content">
        <Leaderboard />
        <Newsfeed />
        <Footer />
      </main>
    </div>
  );
}