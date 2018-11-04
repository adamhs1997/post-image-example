from flask import Flask, request, jsonify

app = Flask(__name__)


# root
@app.route("/")
def index():
    """
    this is a root dir of my server
    :return: str
    """
    return "This is root!!!!"


# GET
@app.route('/users/<user>')
# Apparently the <user> is actually passed to the funciton
def hello_user(user):
    """
    this serves as a demo purpose
    :param user:
    :return: str
    """
    return "Hello %s!" % user


# POST
@app.route('/api/post_some_data', methods=['POST'])
def get_text_prediction():
    """
    predicts requested text whether it is ham or spam
    :return: json
    """
    print(request.files)
    print(request.files.get("image"))
    with open("new.png", 'wb') as out:
        out.write(request.files.get("image").read())

    return jsonify({'you sent this': request.form.get("text")})

# running web app in local machine
if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)