import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import App from './App.jsx'
import './index.css'
import GoogleLoginButton from './Login.jsx'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <App />
    <GoogleLoginButton />
  </StrictMode>,
)
