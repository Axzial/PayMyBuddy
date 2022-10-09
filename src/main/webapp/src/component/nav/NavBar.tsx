import React, {useState} from 'react';
import Link from "next/link";
import Button from "@component/button/Button";
import {useSelector} from "react-redux";
import {RootState} from "@pages/_app";

interface NavLink {
    name: string;
    path: string;
    auth: boolean;
}

export const links: NavLink[] = [
    {
        name: "Home",
        path: "/",
        auth: false
    },
    {
        name: "Profile",
        path: "/profile",
        auth: true
    },
]

export const authLinks: NavLink[] = [
    {
        name: "Login",
        path: "/login",
        auth: false
    },
    {
        name: "Register",
        path: "/register",
        auth: false
    }
]

const NavBar = () => {

    const isAuth = useSelector((state: RootState) => state.authReducer.isAuth);

    return (
        <>
            <div className={"absolute z-20 h-[8vh] w-full flex flex-row place-items-center"}>
                <div className={"flex place-items-center justify-center h-full w-2/6 md:w-3/6"}>
                    <p className={"font-raleway text-green-300 text-2xl font-bold"}>PayMyBuddy</p>
                </div>
                <div className={"flex place-items-center justify-center h-full w-full md:hidden"}>
                    {links.filter(x => x.auth === isAuth).map((v, i, a) =>
                        <div className={"mx-[2vw] cursor-pointer"} key={i}>
                            <Link href={v.path}>
                                <p className={"font-raleway text-xl font-medium text-white"}>{v.name}</p>
                            </Link>
                        </div>
                    )}
                </div>
                {!isAuth ?
                    <div className={"flex place-items-center justify-evenly h-full w-2/6 md:hidden"}>
                        <Button href={"/login"}>
                            <p className={"px-5 text-lg"}>Login</p>
                        </Button>
                        <Button href={"/register"}>
                            <p className={"px-5 text-lg"}>Register</p>
                        </Button>
                    </div>
                    :
                    <div className={"flex place-items-center justify-evenly h-full w-2/6 md:hidden"}>
                        <Button href={"/logout"}>
                            <p className={"px-5 text-lg"}>Logout</p>
                        </Button>
                    </div>
                }
            </div>
        </>
    );
};

export default NavBar;
