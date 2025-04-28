import NewPost from './NewPost.js';
export default function  Newsfeed() {
    const posts = [
        {
            id: 1,
            author: "John Doe",
            time: "2025-04-03T22:25:00",
            content: "Total Score: 37\nI rose to rank 22!\nWordle 1,339 6/6\n\n\n⬛⬛🟩🟨⬛\n⬛🟨🟩🟩⬛\n🟩⬛🟩🟩⬛\n🟩🟨🟩🟩⬛\n🟩⬛🟩🟩🟩\n🟩🟩🟩🟩🟩"
        },
        {
            id: 2,
            author: "Jane Smith",
            time: "2025-04-03T22:00:00",
            content: "Total Score: 37\nI rose to rank 22!\nWordle 1,339 6/6\n\n\n⬛⬛🟩🟨⬛\n⬛🟨🟩🟩⬛\n🟩⬛🟩🟩⬛\n🟩🟨🟩🟩⬛\n🟩⬛🟩🟩🟩\n🟩🟩🟩🟩🟩"
        },
        {
            id: 3,
            author: "Alex Johnson",
            time: "2025-04-03T21:00:00",
            content: "Total Score: 37\nI rose to rank 22!\nWordle 1,339 6/6\n\n\n⬛⬛🟩🟨⬛\n⬛🟨🟩🟩⬛\n🟩⬛🟩🟩⬛\n🟩🟨🟩🟩⬛\n🟩⬛🟩🟩🟩\n🟩🟩🟩🟩🟩"
        }
    ];
    const getTimeSince = (timestamp) => {
        const seconds = Math.floor((new Date() - new Date(timestamp).getTime()) / 1000);
        
        if (seconds < 60) return "Just now";
        
        const minutes = Math.floor(seconds / 60);
        if (minutes < 60) return `${minutes} minutes ago`;
        
        const hours = Math.floor(minutes / 60);
        if (hours < 24) return `${hours} hours ago`;
        
        const days = Math.floor(hours / 24);
        if (days < 30) return `${days} days ago`;
        
        const months = Math.floor(days / 30);
        if (months < 12) return `${months} months ago`;
        
        const years = Math.floor(months / 12);
        return `${years} years ago`;
    };

    return (
        <div class="news-feed">
            <NewPost />
            <div class="feed-container">
                {posts.map(post => (
                    <div class="post" key={post.id}>
                        <div class="post-header">
                            <span class="author">{post.author}</span>
                            <span class="time">{getTimeSince(post.time)}</span>
                        </div>
                        <div class="post-content" style={{ whiteSpace: 'pre-line' }}>
                            {post.content}
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
}