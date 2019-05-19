
package com.mminf.mensafontenuova;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;


//SALDO


public class Menu1 extends Fragment {
    public void scrivi_str(String campo, String valore) {
        SharedPreferences mPreferences_agg = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mPreferences_agg.edit().putString(campo, valore).commit();
    }



    public void scrivi_int(String campo, int valore) {
        SharedPreferences mPreferences3 = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mPreferences3.edit().putInt(campo, valore).commit();
    }

    public String leggi_str(String campo) {
        SharedPreferences mPreferences_leg = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String valore = "";
        return mPreferences_leg.getString(campo, valore);
    }

    public String leggi_sito(String campo, String valore) {
        SharedPreferences mPreferences_leg = PreferenceManager.getDefaultSharedPreferences(getActivity());
        return mPreferences_leg.getString(campo, valore);
    }


    public int leggi_int(String campo) {
        SharedPreferences mPreferences_leg = PreferenceManager.getDefaultSharedPreferences(getActivity());
        int valore = 0;
        return mPreferences_leg.getInt(campo, valore);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments


        View v = inflater.inflate(R.layout.fragment_menu_2, container, false);

        //  Intent intent = new Intent(getActivity(), ITCutiesReaderAppActivity.class);
        // startActivity(intent);


//
        //  WebView myWebView =  v.findViewById(R.id.SCUOLA);
        //      myWebView.setInitialScale(80);
        //      myWebView.getSettings().setBuiltInZoomControls(true);
        //      myWebView.getSettings().setDisplayZoomControls(false);
        //       myWebView.getSettings().setDomStorageEnabled(true);


        TextView bambino = v.findViewById(R.id.textView2);
        TextView bambino2 = v.findViewById(R.id.textView4);
        bambino.setVisibility(getView().VISIBLE);
        bambino2.setVisibility(getView().VISIBLE);
        bambino2.setText("Per visualizzare il saldo attuale");
        bambino.setText("Impostare le credenziali nelle impostazioni");

        if (leggi_str("connesso").equals("ok")) {
            bambino.setText("");


            bambino.setText("Aggiornamento dati saldo in corso");
            WebView myWebView = v.findViewById(R.id.WEB);
            WebSettings webSettings = myWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            myWebView.getSettings().setDomStorageEnabled(true);
            myWebView.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
            String username_sito = leggi_str("username");
            String password_sito = leggi_str("password");


            String url = "https://www.schoolesuite.it/default1/NSC_Login.aspx?installation_code=fontenuopre";
            final String js = "javascript: document.getElementById('txtUsername').value='" + username_sito + "';" + "document.getElementById('txtPassword').value='" + password_sito + "';" + "document.getElementById('btnOK').click()";
            myWebView.loadUrl(url);
            myWebView.setWebViewClient(new WebViewClient() {
                public void onPageFinished(WebView view, String url) {

                    if (url.contains("fontenuopre")) {
                        view.evaluateJavascript(js, new ValueCallback<String>() {
                            @Override
                            public void onReceiveValue(String s) {
                                Log.e("html", js);
                            }
                        });
                    }


                    if (url.contains("login")) {
                        TextView bambino = getView().findViewById(R.id.textView2);
                        bambino.setText("errore login");
                    }
                    if (url.contains("PWM_ChildrenList.aspx")) {


                        view.evaluateJavascript("(function() { return (document.getElementById('tblChildrenList').rows.length); })();", new ValueCallback<String>() {

                                    @Override
                                    public void onReceiveValue(String html21) {
//                    i= Integer.parseInt(html21);

                                        scrivi_int("bambini", Integer.parseInt(html21));
                                        Log.e("Contalinee", html21);

                                    }
                                }
                        );

                        int contarow = leggi_int("bambini");

                        Log.e("contatore", Integer.toString(leggi_int("bambini")));


                        if (contarow == 2) {
                            //                    CARICA NOME BAMBINO
                            view.evaluateJavascript("(function() { return (document.getElementById('tblChildrenList').rows[1].cells.item(0).innerHTML); })();", new ValueCallback<String>() {
                                @Override


                                public void onReceiveValue(String html1) {
                                    String input = html1;
                                    Log.e("bambino", html1);
                                    input = input.substring(html1.indexOf(">") + 1, html1.lastIndexOf("\\"));
                                    input = input;
                                    Log.e("bambino", input);
                                    TextView bambino = getView().findViewById(R.id.textView4);
                                    bambino.setText(input);
                                    bambino.setVisibility(getView().VISIBLE);
                                }
                            });

//                        CARICA SALDO
                            view.evaluateJavascript(
                                    "(function() { return (document.getElementById('tblChildrenList').rows[1].cells.item(1).innerHTML); })();", new ValueCallback<String>() {
                                        @Override
                                        public void onReceiveValue(String html2) {
                                            String input2 = html2;

                                            input2 = input2.substring(html2.indexOf(">") + 1, html2.lastIndexOf("\\"));
                                            input2 = "Saldo: " + input2;
                                            TextView saldo = getView().findViewById(R.id.textView2);
                                            saldo.setText(input2);
                                            saldo.setVisibility(getView().VISIBLE);

                                        }
                                    });


                        }

// ************************************************************************* DUE BAMBINI ********************************************************
//                    ****************************************************************************************************************


                        if (contarow == 3) {

                            //                    CARICA NOME BAMBINO
                            view.evaluateJavascript("(function() { return (document.getElementById('tblChildrenList').rows[1].cells.item(0).innerHTML); })();", new ValueCallback<String>() {
                                @Override


                                public void onReceiveValue(String html1) {
                                    String input = html1;
                                    Log.e("bambino1", html1);
                                    input = input.substring(html1.indexOf(">") + 1, html1.lastIndexOf("\\"));
                                    input = "Bambino: " + input;
                                    Log.e("bambino", input);
                                    TextView bambino = getView().findViewById(R.id.textView4);
                                    bambino.setText(input);
                                    bambino.setVisibility(getView().VISIBLE);
                                }
                            });

//                        CARICA SALDO
                            view.evaluateJavascript(
                                    "(function() { return (document.getElementById('tblChildrenList').rows[1].cells.item(1).innerHTML); })();", new ValueCallback<String>() {
                                        @Override
                                        public void onReceiveValue(String html2) {
                                            String input2 = html2;

                                            input2 = input2.substring(html2.indexOf(">") + 1, html2.lastIndexOf("\\"));
                                            input2 = "Saldo: " + input2;
                                            scrivi_str("saldoappo1", input2);

                                            TextView saldo = getView().findViewById(R.id.textView2);
                                            saldo.setText(input2);
                                            saldo.setVisibility(getView().VISIBLE);
                                        }
                                    });


//                       CARICA NOME BAMBINO
                            view.evaluateJavascript("(function() { return (document.getElementById('tblChildrenList').rows[2].cells.item(0).innerHTML); })();", new ValueCallback<String>() {
                                @Override


                                public void onReceiveValue(String html1) {
                                    String input = html1;
                                    Log.e("bambino", html1);
                                    input = input.substring(html1.indexOf(">") + 1, html1.lastIndexOf("\\"));
                                    input = "Bambino: " + input;
                                    scrivi_str("bambinoappo2", input);
                                    Log.e("bambino", input);
                                    TextView bambino2 = getView().findViewById(R.id.textView6);
                                    bambino2.setText(input);
                                }
                            });

//                        CARICA SALDO
                            view.evaluateJavascript(
                                    "(function() { return (document.getElementById('tblChildrenList').rows[2].cells.item(1).innerHTML); })();", new ValueCallback<String>() {
                                        @Override
                                        public void onReceiveValue(String html2) {
                                            String input2 = html2;

                                            input2 = input2.substring(html2.indexOf(">") + 1, html2.lastIndexOf("\\"));
                                            input2 = "Saldo: " + input2;
                                            scrivi_str("saldoappo2", input2);
                                            TextView saldo2 = getView().findViewById(R.id.textView7);
                                            saldo2.setText(input2);
                                        }
                                    });


                        }
// *********************************************************************************************************************
//                    *****************************************************************************************************


                        // ************************************************************************* tre BAMBINI ********************************************************
//                    ****************************************************************************************************************


                        if (contarow == 4) {

                            //                    CARICA NOME BAMBINO
                            view.evaluateJavascript("(function() { return (document.getElementById('tblChildrenList').rows[1].cells.item(0).innerHTML); })();", new ValueCallback<String>() {
                                @Override


                                public void onReceiveValue(String html1) {
                                    String input = html1;
                                    Log.e("bambino1", html1);
                                    input = input.substring(html1.indexOf(">") + 1, html1.lastIndexOf("\\"));
                                    input = "Bambino: " + input;
                                    Log.e("bambino", input);
                                    TextView bambino = getView().findViewById(R.id.textView4);
                                    bambino.setText(input);

                                }
                            });

//                        CARICA SALDO
                            view.evaluateJavascript(
                                    "(function() { return (document.getElementById('tblChildrenList').rows[1].cells.item(1).innerHTML); })();", new ValueCallback<String>() {
                                        @Override
                                        public void onReceiveValue(String html2) {
                                            String input2 = html2;

                                            input2 = input2.substring(html2.indexOf(">") + 1, html2.lastIndexOf("\\"));
                                            input2 = "Saldo: " + input2;
                                            scrivi_str("saldoappo1", input2);

                                            TextView saldo = getView().findViewById(R.id.textView2);
                                            saldo.setText(input2);
                                        }
                                    });


//                       CARICA NOME BAMBINO
                            view.evaluateJavascript("(function() { return (document.getElementById('tblChildrenList').rows[2].cells.item(0).innerHTML); })();", new ValueCallback<String>() {
                                @Override


                                public void onReceiveValue(String html1) {
                                    String input = html1;
                                    Log.e("bambino", html1);
                                    input = input.substring(html1.indexOf(">") + 1, html1.lastIndexOf("\\"));
                                    input = "Bambino: " + input;
                                    scrivi_str("bambinoappo2", input);
                                    Log.e("bambino", input);
                                    TextView bambino2 = getView().findViewById(R.id.textView6);
                                    bambino2.setText(input);
                                }
                            });

//                        CARICA SALDO
                            view.evaluateJavascript(
                                    "(function() { return (document.getElementById('tblChildrenList').rows[2].cells.item(1).innerHTML); })();", new ValueCallback<String>() {
                                        @Override
                                        public void onReceiveValue(String html2) {
                                            String input2 = html2;

                                            input2 = input2.substring(html2.indexOf(">") + 1, html2.lastIndexOf("\\"));
                                            input2 = "Saldo: " + input2;
                                            scrivi_str("saldoappo2", input2);
                                            TextView saldo2 = getView().findViewById(R.id.textView7);
                                            saldo2.setText(input2);
                                        }
                                    });

                            view.evaluateJavascript("(function() { return (document.getElementById('tblChildrenList').rows[3].cells.item(0).innerHTML); })();", new ValueCallback<String>() {
                                @Override


                                public void onReceiveValue(String html1) {
                                    String input = html1;
                                    Log.e("bambino", html1);
                                    input = input.substring(html1.indexOf(">") + 1, html1.lastIndexOf("\\"));
                                    input = "Bambino: " + input;
                                    scrivi_str("bambinoappo2", input);
                                    Log.e("bambino", input);
                                    TextView bambino2 = getView().findViewById(R.id.textView8);
                                    bambino2.setText(input);
                                }
                            });

//                        CARICA SALDO
                            view.evaluateJavascript(
                                    "(function() { return (document.getElementById('tblChildrenList').rows[3].cells.item(1).innerHTML); })();", new ValueCallback<String>() {
                                        @Override
                                        public void onReceiveValue(String html2) {
                                            String input2 = html2;

                                            input2 = input2.substring(html2.indexOf(">") + 1, html2.lastIndexOf("\\"));
                                            input2 = "Saldo: " + input2;
                                            scrivi_str("saldoappo2", input2);
                                            TextView saldo2 = getView().findViewById(R.id.textView9);
                                            saldo2.setText(input2);
                                        }
                                    });
                        }
// *********************************************************************************************************************
//                    *****************************************************************************************************

                        // ************************************************************************* quattro BAMBINI ********************************************************
//                    ****************************************************************************************************************


                        if (contarow == 5) {

                            //                    CARICA NOME BAMBINO
                            view.evaluateJavascript("(function() { return (document.getElementById('tblChildrenList').rows[1].cells.item(0).innerHTML); })();", new ValueCallback<String>() {
                                @Override


                                public void onReceiveValue(String html1) {
                                    String input = html1;
                                    Log.e("bambino1", html1);
                                    input = input.substring(html1.indexOf(">") + 1, html1.lastIndexOf("\\"));
                                    input = "Bambino: " + input;
                                    Log.e("bambino", input);
                                    TextView bambino = getView().findViewById(R.id.textView4);
                                    bambino.setText(input);

                                }
                            });

//                        CARICA SALDO
                            view.evaluateJavascript(
                                    "(function() { return (document.getElementById('tblChildrenList').rows[1].cells.item(1).innerHTML); })();", new ValueCallback<String>() {
                                        @Override
                                        public void onReceiveValue(String html2) {
                                            String input2 = html2;

                                            input2 = input2.substring(html2.indexOf(">") + 1, html2.lastIndexOf("\\"));
                                            input2 = "Saldo: " + input2;
                                            scrivi_str("saldoappo1", input2);

                                            TextView saldo = getView().findViewById(R.id.textView2);
                                            saldo.setText(input2);
                                        }
                                    });


//                       CARICA NOME BAMBINO
                            view.evaluateJavascript("(function() { return (document.getElementById('tblChildrenList').rows[2].cells.item(0).innerHTML); })();", new ValueCallback<String>() {
                                @Override


                                public void onReceiveValue(String html1) {
                                    String input = html1;
                                    Log.e("bambino", html1);
                                    input = input.substring(html1.indexOf(">") + 1, html1.lastIndexOf("\\"));
                                    input = "Bambino: " + input;
                                    scrivi_str("bambinoappo2", input);
                                    Log.e("bambino", input);
                                    TextView bambino2 = getView().findViewById(R.id.textView6);
                                    bambino2.setText(input);
                                }
                            });

//                        CARICA SALDO
                            view.evaluateJavascript(
                                    "(function() { return (document.getElementById('tblChildrenList').rows[2].cells.item(1).innerHTML); })();", new ValueCallback<String>() {
                                        @Override
                                        public void onReceiveValue(String html2) {
                                            String input2 = html2;

                                            input2 = input2.substring(html2.indexOf(">") + 1, html2.lastIndexOf("\\"));
                                            input2 = "Saldo: " + input2;
                                            scrivi_str("saldoappo2", input2);
                                            TextView saldo2 = getView().findViewById(R.id.textView7);
                                            saldo2.setText(input2);
                                        }
                                    });

                            view.evaluateJavascript("(function() { return (document.getElementById('tblChildrenList').rows[3].cells.item(0).innerHTML); })();", new ValueCallback<String>() {
                                @Override


                                public void onReceiveValue(String html1) {
                                    String input = html1;
                                    Log.e("bambino", html1);
                                    input = input.substring(html1.indexOf(">") + 1, html1.lastIndexOf("\\"));
                                    input = "Bambino: " + input;
                                    scrivi_str("bambinoappo2", input);
                                    Log.e("bambino", input);
                                    TextView bambino2 = getView().findViewById(R.id.textView8);
                                    bambino2.setText(input);
                                }
                            });

//                        CARICA SALDO
                            view.evaluateJavascript(
                                    "(function() { return (document.getElementById('tblChildrenList').rows[3].cells.item(1).innerHTML); })();", new ValueCallback<String>() {
                                        @Override
                                        public void onReceiveValue(String html2) {
                                            String input2 = html2;

                                            input2 = input2.substring(html2.indexOf(">") + 1, html2.lastIndexOf("\\"));
                                            input2 = "Saldo: " + input2;
                                            scrivi_str("saldoappo2", input2);
                                            TextView saldo2 = getView().findViewById(R.id.textView9);
                                            saldo2.setText(input2);
                                        }
                                    });


                            view.evaluateJavascript("(function() { return (document.getElementById('tblChildrenList').rows[4].cells.item(0).innerHTML); })();", new ValueCallback<String>() {
                                @Override


                                public void onReceiveValue(String html1) {
                                    String input = html1;
                                    Log.e("bambino", html1);
                                    input = input.substring(html1.indexOf(">") + 1, html1.lastIndexOf("\\"));
                                    input = "Bambino: " + input;
                                    scrivi_str("bambinoappo2", input);
                                    Log.e("bambino", input);
                                    TextView bambino2 = getView().findViewById(R.id.textView11);
                                    bambino2.setText(input);
                                }
                            });

//                        CARICA SALDO
                            view.evaluateJavascript(
                                    "(function() { return (document.getElementById('tblChildrenList').rows[4].cells.item(1).innerHTML); })();", new ValueCallback<String>() {
                                        @Override
                                        public void onReceiveValue(String html2) {
                                            String input2 = html2;

                                            input2 = input2.substring(html2.indexOf(">") + 1, html2.lastIndexOf("\\"));
                                            input2 = "Saldo: " + input2;
                                            scrivi_str("saldoappo2", input2);
                                            TextView saldo2 = getView().findViewById(R.id.textView12);
                                            saldo2.setText(input2);
                                        }
                                    });


                        }
// *********************************************************************************************************************
//                    *****************************************************************************************************


                    }
                }


//
//            }

            });


        }



        return v;


    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Saldo Mensa");
    }


}

/*
*  webView.navigationDelegate = self
        tableView.dataSource = self
        tableView.delegate = self
        self.tableView.register(UITableViewCell.self, forCellReuseIdentifier: "Cella")

        // Do any additional setup after loading the view.

        let defaults = UserDefaults.standard
        let  connesso: String = defaults.string(forKey: "connesso") ?? "NO"
        if connesso == "NO" || connesso == "0"
        {
            createAlert (title: "errore credenziali",message: "Impostare le credenziali in impostazioni")
        }

        if (self.text1.text != "error" && connesso == "1" )
        {

            createAlert (title: "Credenziali ok",message: "Attendere il caricamento dei dati")        }
        text1.delegate = self
        text2.delegate = self


        if revealViewController() != nil {
            button_saldo.target = revealViewController()
            button_saldo.action = #selector(SWRevealViewController.revealToggle(_:))
            self.view.addGestureRecognizer(self.revealViewController().panGestureRecognizer())
        }



        let myURL = URL(string:"https://www.schoolesuite.it/default1/NSC_Login.aspx?installation_code=fontenuopre")
        let myRequest = URLRequest(url: myURL!)
        webView.load(myRequest)


    }


    func webView(_ webView: WKWebView, didFinish navigation: WKNavigation!) {
        if (webView == self.webView)
        {


            let defaults = UserDefaults.standard
            let  connesso: String = defaults.string(forKey: "connesso") ?? "NO"


            if connesso == "NO" || connesso == "0"
            {self.text1.text = "impostare credenziali"
                self.label1.text = "impostare credenziali"


            }
            if (self.text1.text != "error" && connesso == "1" )
            {
                self.label1.text = ""
                let defaults = UserDefaults.standard


                let   user: String =  defaults.string(forKey: "user") ?? "NO"
                let   pass: String = defaults.string(forKey: "pass") ?? "NO"
                print(user + "  " + pass)

                webView.evaluateJavaScript("document.getElementById('txtUsername').value='" + user + "';" + "document.getElementById('txtPassword').value='" + pass + "';" + "document.getElementById('btnOK').click()", completionHandler: { result, error  in

                    if error != nil {

                    }
                })




                if (webView.url?.absoluteString.contains("login") ?? false) {
                    self.text1.text = "error"
                }

                if (webView.url?.absoluteString.contains("PWM_ChildrenList.aspx")) ?? false {


                    webView.evaluateJavaScript("(function() { return (document.getElementById('tblChildrenList').rows.length); })();", completionHandler: { result, error in
                        if let height = result as? Int {
                            self.contabambini =  height - 1
                            print("numero righe")
                            print (height)
                        }
                    })
            print("contabambini")
               print(self.contabambini)


                    webView.evaluateJavaScript("(function(){var div=';';var ret;ret =';';var table = document.getElementById('tblChildrenList');for (var i = 1, row; row = table.rows[i]; i++) {col = row.cells[0]; ret = ret + row.cells[0].innerText + div+ row.cells[1].innerText+div;}return ret;})();",completionHandler: { result, error in
                        if let Nome = result as? String {
                        print(Nome ?? "nullo")


                            let appo = String(Nome.dropFirst())    // "ello"
                            let  fullName = String(appo.dropLast())


                            let fullNameArr = fullName.components(separatedBy: ";")


                            for (index, element) in fullNameArr.enumerated() {
                                if index % 2 == 0 {
                                   self.bambini.append(element)
                                } else {
                                self.soldi.append(element)
                                }
                            }



                			self.tableView.reloadData()
                        }})
























                }





            }
        }
*
* */