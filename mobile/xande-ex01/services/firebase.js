import { initializeApp } from 'firebase/app';
import { getAuth } from 'firebase/auth';
import {
        signOut,
        signInWithEmailAndPassword,
        createUserWithEmailAndPassword,
} from "firebase/auth";


const firebaseConfig = {
    apiKey: "AIzaSyB7-eZ6ix7vGTwm8R_dDQv0zX7t_HFr7as",
    authDomain: "xandeex02.firebaseapp.com",
    projectId: "xandeex02",
    storageBucket: "xandeex02.firebasestorage.app",
    messagingSenderId: "765869186747",
    appId: "1:765869186747:web:8883ca69884938ea61539e"
};

const app = initializeApp(firebaseConfig);

export const auth = getAuth(app);

export const registrar = async (email, senha) => {
        try {
                await createUserWithEmailAndPassword(
                        auth,
                        email,
                        senha
                );
                alert("Usuário registrado com sucesso!");
        } catch (error) {
                alert("Erro: " + error.message);
        }
};

export const login = async (email, senha) => {
        try {
                await signInWithEmailAndPassword(auth, email, senha);
                alert("Login realizado!");
        } catch (error) {
                alert("Erro: " + error.message);
        }
};

export const logout = async () => {
        await signOut(auth);
};