import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

function Home() {
  const navigate = useNavigate();
  const [categories, setCategories] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [selectedCategory, setSelectedCategory] = useState(null);
  const [difficulties] = useState(['easy', 'medium', 'hard']);

  useEffect(() => {
    const fetchCategories = async () => {
      try {
        setLoading(true);
        setError(null);
        const response = await fetch('http://localhost:8080/api/quiz/questions/getCategories');
        console.log(response);
        if (!response.ok) {
          throw new Error('Failed to fetch categories');
        }
        const data = await response.json();
        setCategories(data.categories);
      } catch (error) {
        console.error('Error fetching categories:', error);
        setError('Failed to load categories. Please try again later.');
      } finally {
        setLoading(false);
      }
    };

    fetchCategories();
  }, []);

  const handleCategorySelect = (category) => {
    setSelectedCategory(category);
  };

  const handleDifficultySelect = (difficulty) => {
    navigate(`/quiz/${selectedCategory}/${difficulty}`);
  };

  if (loading) {
    return (
      <div className="text-center">
        <div className="flex justify-center items-center h-64">
          <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-500"></div>
        </div>
      </div>
    );
  }

  if (error) {
    return (
      <div className="text-center">
        <div className="bg-red-50 border border-red-200 rounded-lg p-6">
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

  return (
    <div className="text-center">
      <h1 className="text-4xl font-bold text-gray-900 mb-8">Welcome to Quiz App</h1>
      {!selectedCategory ? (
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          {categories.map((category) => (
            <div key={category} className="bg-white p-6 rounded-lg shadow-md">
              <h2 className="text-2xl font-semibold mb-4">{category}</h2>
              <p className="text-gray-600 mb-4">Test your knowledge in this category</p>
              <button 
                onClick={() => handleCategorySelect(category)}
                className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600"
              >
                Select Category
              </button>
            </div>
          ))}
        </div>
      ) : (
        <div className="max-w-md mx-auto">
          <h2 className="text-2xl font-semibold mb-6">Select Difficulty for {selectedCategory}</h2>
          <div className="space-y-4">
            {difficulties.map((difficulty) => (
              <button
                key={difficulty}
                onClick={() => handleDifficultySelect(difficulty)}
                className="w-full bg-white p-4 rounded-lg shadow-md hover:bg-gray-50 text-left capitalize"
              >
                {difficulty}
              </button>
            ))}
            <button
              onClick={() => setSelectedCategory(null)}
              className="w-full bg-gray-500 text-white p-4 rounded-lg shadow-md hover:bg-gray-600"
            >
              Back to Categories
            </button>
          </div>
        </div>
      )}
    </div>
  );
}

export default Home; 