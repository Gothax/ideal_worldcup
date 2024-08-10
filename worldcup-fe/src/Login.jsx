import {GoogleLogin} from "@react-oauth/google";
import {GoogleOAuthProvider} from "@react-oauth/google";
import axios from "axios";

const GoogleLoginButton = () => {
    const clientId = "405722211943-pos46fpdksbapn9ts2cef64qs3v494ru.apps.googleusercontent.com"

    const handleLoginSuccess = async (res) => {
        console.log(res);
        const idToken = res.credential;

        try{
            const response = await axios.get("http://localhost:8080/auth/success", {idToken});
            console.log(response);
        } catch (error) {
            console.log(error);
        }
    }

    const handleLoginFailure = (err) => {
        console.log(err);
    }

    return (
        <>
            <GoogleOAuthProvider clientId={clientId}>
                <GoogleLogin
                    onSuccess={handleLoginSuccess}
                    onFailure={handleLoginFailure}
                />
            </GoogleOAuthProvider>
        </>
    );
};

export default GoogleLoginButton
