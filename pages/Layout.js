import Head from 'next/head'

export default function Layout(
    { children }
) {
    return (
      <div>
        <Head>
            <title>ODAAT</title>
            <meta name="description" content="Odaat App" />
            <link rel="icon" href="/favicon.ico" />
        </Head>
        { children }
      </div>
    )
  }