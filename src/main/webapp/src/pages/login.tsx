import React, {useState} from 'react'
import Link from 'next/link';
import {useAppDispatch} from './_app';
import Divider from '@component/Divider';
import {SubmitHandler, UnpackNestedValue, useForm} from "react-hook-form";
import * as Yup from "yup";
import {yupResolver} from "@hookform/resolvers/yup";
import Base from "@component/layout/Base";
import {MdErrorOutline, MdOutlineEmail, MdOutlineLock} from "react-icons/md";
import {BsEye, BsEyeSlash} from "react-icons/bs";
import Button from '@component/button/Button';
import {authSlice, login} from "@store/auth/auth.slice";
import { useRouter } from 'next/router';
import {toast} from "react-toastify";

interface LoginFormInputs {
    email: string
    password: string
}

const loginSchema = Yup.object({
    email: Yup.string()
        .email('Please provide a valid E-Mail.')
        .required('This field must be provided.'),
    password: Yup.string()
        .required('This field must be provided.')
        .min(8, 'Password must contain at least 8 characters.')
        .max(16, 'Password must contain at max 16 characters')
})

const LoginPage = () => {

    const {register, handleSubmit, watch, formState: {errors}} = useForm<LoginFormInputs>({
        resolver: yupResolver(loginSchema)
    });

    const router = useRouter();
    const dispatch = useAppDispatch();

    const [showPassword, setShowPassword] = useState<boolean>(false);

    const submitLogin: SubmitHandler<LoginFormInputs> = (data: UnpackNestedValue<LoginFormInputs>) => {
        dispatch(login({email: data.email, password: data.password}))
            .catch(err => toast.error(err))
            .then(() => dispatch(authSlice.actions.setAuth(true)))
            .then(() => router.push("/profile"));
    }

    return (
        <Base>
            <div className={"flex place-items-center justify-center h-screen w-screen bg-neutral-600"}>
                <form onSubmit={handleSubmit(submitLogin)}
                      className={"shadow-2xl bg-neutral-700 w-3/12 md:w-10/12 rounded-md flex flex-col place-items-center justify-between px-[3vw] py-[5vh]"}>

                    <p className={"text-4xl mb-[5vh] font-sans text-white"}>Login</p>

                    <div className={"w-full mb-[2vh]"}>
                        <label htmlFor="email" className="block text-sm font-medium text-white">
                            Email
                        </label>
                        <div className="mt-2 relative rounded-md shadow-sm">
                            <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                                <MdOutlineEmail className="h-5 w-5 text-white" aria-hidden="true"/>
                            </div>
                            <input
                                type="email"
                                id="email"
                                className="bg-neutral-800 border-none text-white font-raleway font-medium block w-full pl-10 rounded-md hover:bg-neutral-900"
                                placeholder="you@example.com"
                                {...register("email")}
                            />
                        </div>
                        {errors?.email &&
                            <div className={"flex flex-row place-items-center justify-start pt-1"}>
                                <MdErrorOutline className="h-5 w-5 text-red-500" aria-hidden="true"/>
                                <p className={"font-raleway pl-1 text-red-500"}>{errors?.email?.message}</p>
                            </div>
                        }
                    </div>

                    <div className={"w-full mb-[6vh]"}>
                        <label htmlFor="password" className="block text-sm font-medium text-white">
                            Password
                        </label>
                        <div className="mt-2 flex rounded-md shadow-sm">
                            <div className="relative flex items-stretch flex-grow focus-within:z-10">
                                <div
                                    className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                                    <MdOutlineLock className="h-5 w-5 text-white" aria-hidden="true"/>
                                </div>
                                <input
                                    type={showPassword ? "text" : "password"}
                                    id="password"
                                    autoComplete={"current-password"}
                                    className="bg-neutral-800 border-none text-white font-raleway font-medium block w-full rounded-none rounded-l-md pl-10 hover:bg-neutral-900"
                                    placeholder="Password"
                                    {...register("password")}
                                />
                            </div>
                            <button
                                onClick={() => setShowPassword(!showPassword)}
                                type="button"
                                className="bg-neutral-800 -ml-px relative inline-flex items-center space-x-2 px-4 py-2 text-sm font-medium rounded-r-md text-white hover:bg-neutral-900 "
                            >
                                {showPassword ? <BsEye className="h-5 w-5 text-white" aria-hidden="true"/> :
                                    <BsEyeSlash className="h-5 w-5 text-white" aria-hidden="true"/>}
                            </button>
                        </div>
                        {errors?.password &&
                            <div className={"flex flex-row place-items-center justify-start pt-1"}>
                                <MdErrorOutline className="h-5 w-5 text-red-500" aria-hidden="true"/>
                                <p className={"font-raleway pl-1 text-red-500"}>{errors?.password?.message}</p>
                            </div>
                        }
                    </div>

                    <div className={"w-full flex flex-col justify-center place-items-center gap-3"}>
                        <Button type={"submit"} className={"w-4/6"}>
                            <p className={"font-sans text-white text-lg px-14"}>Login</p>
                        </Button>

                        <Divider text={"or"}/>

                        <Link href={"/register"}>
                            <Button className={"w-4/6"}>
                                <p className={"font-sans text-white text-lg px-14"}>Register</p>
                            </Button>
                        </Link>
                    </div>
                </form>
            </div>
        </Base>
    );

};

export default LoginPage;
