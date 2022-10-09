import axios, {AxiosResponse} from 'axios';
import Routes from '../config/ApiConfig';
import ApiConfig from '../config/ApiConfig';

class AuthService {
    login = (data: LoginDTO): Promise<AxiosResponse<TokenDTO>> => {
        return axios.post<TokenDTO>(ApiConfig.BASE_URL + Routes.AUTH_LOGIN_PATH, data);
    }

    register = (data: RegisterDTO): Promise<AxiosResponse<TokenDTO>> => {
        return axios.post<TokenDTO>(ApiConfig.BASE_URL + Routes.AUTH_REGISTER_PATH, data);
    }
}

export default new AuthService();


export class TokenDTO {

    token?: string;

    constructor(data?: Partial<TokenDTO>) {
        Object.assign(this, data);
    }
}

export class LoginDTO {

    email?: string;
    password?: string;

    constructor(data?: Partial<LoginDTO>) {
        Object.assign(this, data);
    }
}



export class RegisterDTO {

    email?: string;
    username?: string;
    password?: string;

    constructor(data?: Partial<RegisterDTO>) {
        Object.assign(this, data);
    }
}

