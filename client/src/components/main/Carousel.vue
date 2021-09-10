<template>
  <div>
    <button class="round-button" @click="rotateLeft">
      <img src="@/assets/arrow_left.svg" alt="arrow_left">
    </button>
    <div class="carousel-wrapper">
      <ul class="carousel-list" :style="{width: width + 'px'}">
        <carousel-node v-for="(card, idx) in cards" :key="card.key" class="card"
                       :card="card"
                       :scaling-factor="scalingFactor"
                       :shift="shift"
                       :levels="normalizedLevels"
                       :onClick="onClickFn(idx)"
        />
      </ul>
    </div>
    <button class="round-button" @click="rotateRight">
      <img src="@/assets/arrow_right.svg" alt="arrow_right">
    </button>
  </div>
</template>

<script>
import {KeyGenerator} from "../../utils"

const CarouselNode = {
  props: ['card', 'scalingFactor', 'shift', 'levels', 'onClick'],
  computed: {
    scale: function () {
      return Math.pow(this.scalingFactor, Math.abs(this.card.pos))
    },
    translate: function () {
      return Math.sign(this.card.pos) * this.shift * (1 + (this.scale - this.scalingFactor) / (this.scalingFactor - 1)) * 100
    },
    zIndex: function () {
      return this.levels - Math.abs(this.card.pos) - 1
    },
    opacity: function () {
      return (Math.abs(this.card.pos) < this.levels) ? 1 : 0
    }
  },
  render(createElement) {
    return createElement(
        'li',
        {
          style: {
            transform: 'translateX(' + this.translate + '%) scale(' + this.scale + ')',
            zIndex: this.zIndex,
            opacity: this.opacity
          },
          on: {
            'click': this.onClick
          }
        },
        Array(this.card.node)
    )
  }
}

export default {
  name: 'Carousel',
  components: {
    'carousel-node': CarouselNode
  },
  props: ['levels', 'scalingFactor', 'shift'],
  data() {
    return {
      cards: [],
      width: 0
    }
  },
  computed: {
    normalizedLevels: function () {
      return Math.min(Math.floor((this.cards.length + 1) / 2), this.levels)
    },
  },
  created() {
    this.initCards()
  },
  mounted() {
    this.updateWidth()
  },
  methods: {
    initCards: function () {
      const keyGenerator = new KeyGenerator()
      this.cards = this.$slots.default.map((node, idx) => ({node: node, pos: idx, key: keyGenerator.next()}))
      this.rotateToCard(this.cards[0])
    },
    rotateLeft: function () {
      this.rotate(1)
    },
    rotateRight: function () {
      this.rotate(-1)
    },
    rotateToCard: function (card) {
      this.rotate(-card.pos)
    },
    rotate: function (distance) {
      const size = this.cards.length
      if (distance < 0) distance += size
      this.cards.forEach(card => {
        let newPos = card.pos + distance
        if (newPos >= size / 2) newPos -= size
        card.pos = newPos
      })
      this.$nextTick(this.updateWidth)
    },
    onClickFn: function (idx) {
      return () => this.rotateToCard(this.cards[idx])
    },
    updateWidth: function () {
      this.width = this.cards
          .filter(c => Math.abs(c.pos) < this.normalizedLevels)
          .map(this.cardWidth)
          .reduce((acc, w) => acc + w, 0)
    },
    cardWidth: function (card) {
      if (card.node.elm === undefined) {
        return 0
      }
      let baseWidth = card.node.elm.clientWidth
      if (card.pos === 0) {
        return baseWidth
      }
      let pos = Math.abs(card.pos)
      let width = baseWidth * Math.pow(this.scalingFactor, pos - 1)
      return width * this.shift - width / 2 + width / 2 * this.scalingFactor
    }
  }
}

</script>

<style scoped>

.carousel-wrapper {
  display: inline-block;
  vertical-align: middle;
}

.round-button {
  border-radius: 50%;
  width: 3.5rem;
  height: 3.5rem;
  margin: 0 3rem;
}

.round-button img {
  vertical-align: middle;
}

.carousel-list {
  height: 34rem;
  width: 100%;
  display: flex;
  position: relative;
  justify-content: center;
}

.card {
  display: flex;
  position: absolute;
  transition: all .3s ease-in;
}

</style>