module.exports = {
    mode: "jit",
    plugins: [
        require('@tailwindcss/forms'),
    ],
    content: [
        "./src/**/*.{js,ts,jsx,tsx}"
    ],
    variants: {
        textColor: ['responsive', 'hover', 'focus', 'group-hover'],
    },
    theme: {
        extend: {
            fontFamily: {
                'raleway': ['Raleway', 'sans-serif'],
            },
            screens: {
                '2xl': {'max': '1535px'},
                'xl': {'max': '1279px'},
                'lg': {'max': '1023px'},
                'md': {'max': '767px'},
                'sm': {'max': '639px'},
            },
        },
    },
}
