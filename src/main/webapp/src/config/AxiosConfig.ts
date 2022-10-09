import axios, {AxiosError} from 'axios';
import ApiErrorModel from '@model/api/api-error.model';

// @ts-ignore
axios.defaults.headers['Access-Control-Allow-Origin'] = '*';

axios.interceptors.response.use(
    response => response,
    error => Promise.reject(error as AxiosError<ApiErrorModel>)
)
