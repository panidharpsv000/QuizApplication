import React, { useState, useEffect } from 'react';

function History() {
  const [history, setHistory] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const user = JSON.parse(localStorage.getItem('user'));
  useEffect(() => {
    const fetchHistory = async () => {
      try {
        setLoading(true);
        setError(null);
        const response = await fetch(`http://localhost:8080/api/quiz/history/getHistory/${user.id}`);
        if (!response.ok) {
          throw new Error('Failed to fetch quiz history');
        }
        const data = await response.json();
        setHistory(data);
      } catch (error) {
        console.error('Error fetching quiz history:', error);
        setError('Failed to load quiz history. Please try again later.');
      } finally {
        setLoading(false);
      }
    };

    fetchHistory();
  }, []);

  if (loading) {
    return (
      <div className="max-w-2xl mx-auto">
        <div className="flex justify-center items-center h-64">
          <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-500"></div>
        </div>
      </div>
    );
  }

  if (error) {
    return (
      <div className="max-w-2xl mx-auto">
        <div className="bg-red-50 border border-red-200 rounded-lg p-6 text-center">
          <p className="text-red-600">{error}</p>
          <button 
            onClick={() => window.location.reload()} 
            className="mt-4 bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600"
          >
            Try Again
          </button>
        </div>
      </div>
    );
  }

  if (!history || history.length === 0) {
    return (
      <div className="max-w-2xl mx-auto">
        <div className="bg-white rounded-lg shadow-md p-6 text-center">
          <h1 className="text-3xl font-bold text-gray-900 mb-4">Quiz History</h1>
          <p className="text-gray-600">No quiz history available yet.</p>
        </div>
      </div>
    );
  }

  return (
    <div className="max-w-2xl mx-auto">
      <h1 className="text-3xl font-bold text-gray-900 mb-8 text-center">Quiz History</h1>
      <div className="bg-white rounded-lg shadow-md">
        <div className="p-4 border-b">
          <h2 className="text-xl font-semibold text-gray-800">Your Past Quizzes</h2>
        </div>
        <div className="divide-y">
          {history.map((quiz, index) => (
            <div key={index} className="p-4">
              <div className="flex justify-between items-center mb-2">
                <div>
                  <span className="text-lg font-medium text-gray-900 capitalize">{quiz.category}</span>
                  <span className="ml-2 text-sm text-gray-500 capitalize">({quiz.difficulty})</span>
                </div>
                <span className="text-sm text-gray-500">
                  {new Date(quiz.date).toLocaleDateString()}
                </span>
              </div>
              <div className="flex justify-between items-center">
                <span className="text-gray-600">Score: {quiz.score}</span>
                <span className="text-gray-600">Total Questions: {quiz.totalQuestions}</span>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}

export default History; 