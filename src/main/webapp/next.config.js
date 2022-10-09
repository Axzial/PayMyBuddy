/** @type {import('next').NextConfig} */
module.exports = {
    reactStrictMode: true,
    images: {
        domains: ['www.gravatar.com', 'i.redd.it', 'picsum.photos'],
        formats: ['image/avif', 'image/webp'],
    },
    i18n: {
        locales: ['en', 'fr'],
        defaultLocale: 'en',
        localeDetection: true
    }
}
